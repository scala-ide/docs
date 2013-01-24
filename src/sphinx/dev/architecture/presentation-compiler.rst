
.. _scalapresentationcompiler:

The Scala Presentation Compiler
===============================

*The original version of this document was written by Mads Hartmann Jensen, in collaboration with Mirco Dotta, while doing research for his masters thesis.*

The Scala IDE for Eclipse uses the Scala Presentation Compiler, a faster asynchronous version of the Scala Compiler. The presentation compiler only runs the phases up until and including the typer phase, that is, the first 4 of the 27 scala compilation phases. The IDE uses the presentation compiler to provide semantic features such as live error markers, inferred type hovers, and semantic highlighting. This document describes the key classes you need to know in order to understand how the Scala IDE uses the presentation compiler and provides some examples of interactions between the IDE and the presentation compiler.


Key Classes
-----------

This section describes the key classes that are responsible for the interaction between the IDE and the presentation compiler.

**ScalaProject** wraps an underlying ``org.eclipse.core.resources.IProject``. ``IProject`` is an interface that describes a project in Eclipse; for instance, it knows where all the files and resources are stored. We use this class for Scala projects as we want to store additional information about a project such as the ``BuildManager`` and the ``ScalaPresentationCompiler``. The ``BuildManager`` is the component responsible of building a project inside Eclipse (i.e., it produces bytecode) and the ``ScalaPresentationCompiler`` offers an interface to communicate with the Scala presentation compiler and it's used to implement all intelligent behavior in the editor, e.g., hyperlinking, code completion etc. To interact with the presentation compiler you can use one of three methods:

::

    withPresentationCompiler[T](op: ScalaPresentationCompiler => T)
                               (orElse: => T = defaultOrElse): T

Which will return the value of invoking the function op with the current presentation compiler or return the default value defined by ``orElse`` if it hasn’t been initialized properly. There is also a version where op doesn’t compute any value but simply performs side-effects. This is a general pattern that the with* methods also have a side-effecting alternative named ``doWith``.

::

    doWithPresentationCompiler(op: ScalaPresentationCompiler => Unit): Unit

Finally you can also use:

::

    withSourceFile[T](scu: InteractiveCompilationUnit)
                     (op: (SourceFile, ScalaPresentationCompiler) => T)
                     (orElse: => T = defaultOrElse): T

This method takes three arguments, the first is an instance of an ``InteractiveCompilationUnit`` (described later). The second argument is a closure that will compute a value based on the source file of the compilation unit, and the current presentation compiler. The third argument is the default value to return in case the presentation compiler isn't properly initialized.  This method is convenient as you often want to use the presentation compiler on a specific source file. The reason you should use these methods and not just access the current presentation compiler directly is that it might be replaced with a new presentation compiler at any given time. That is, if you don’t use these method and instead keep a reference to the current presentation compiler you might end up using one that isn’t valid anymore and you will get wrong results. One instance where it is replaced is when the classpath changes, e.g. when adding/removing jars to the project.

**PresentationCompiler** the presentation compiler is an asynchronous and interruptible component for targeted type-checking of Scala source that runs in its own thread. Some of the more important methods to interact with the presentation compiler are:

::

    withParseTree[T](sourceFile: SourceFile)(op: Tree => T): T

Given a SourceFile it will use the presentation compiler to parse the source and hand the parsed tree to the closure op and return the value it computes. op will not run on the presentation compiler thread; if you want to do so you should use the ``askOption`` method (described later).

::

    withStructure[T](sourceFile: SourceFile, keepLoaded: Boolean = false)
        (op: Tree => T): T

This is a bit more tricky; if the source file hasn’t been seen by the presentation compiler it will give ``op`` a parse tree where all the top-level symbols have been evaluated (the rest is unevaluated, as it’s lazy, see the section :ref:`risks`). The second time it will give ``op`` a completely type-checked tree. This is for example used to create the outline view in the editor. Ideally you want to base the outline view on a typed tree so you can make it as precise as possible; for instance inferred types are only available after the typer phase. However, using the typed tree is not always practical as it can potentially take some time to compute, especially on large files. To avoid forcing the user to wait for the typed tree we initially create the outline view based on the parsed tree so it's accessible instantly and then refine it once a typed tree is available. The boolean keepLoaded defines if the presentation compiler should hang on to the loaded compilation unit afterwards or discard it.

::

    askOption[A](op: () => A, timeout: Int): Option[A]

This method will compute something in the presentation compiler thread and return the value. However, if the computation takes more milliseconds that defined by timeout to compute it is aborted and None is returned. There is also a version of this method that uses a default timeout interval. Why you would want to run anything on the presentation compiler thread is explained in the section :ref:`risks`.

**InteractiveCompilationUnit** is a trait for any Scala compilation unit i.e. anything that can be compiled. Concrete implementations will have to provide things such as ``file: AbstractFile``, the ``AbstractFile`` that the Scala compiler uses to read this compilation unit, and ``scalaProject: ScalaProject``, The Scala project to which this compilation unit belongs. When you have a compilation unit you can interact with the presentation compiler using one of two methods:

::

    withSourceFile[T](op: (SourceFile, ScalaPresentationCompiler) => T)
         (orElse: => T = scalaProject.defaultOrElse): T

It returns the result of invoking the function op with the source file related to the current compilation unit and the Scala presentation compiler that is currently in use by the project that owns this compilation unit; if it is unsuccessful it returns the default value given by ``orElse``. The side effecting version is

::

    doWithSourceFile(op: (SourceFile, ScalaPresentationCompiler) => Unit)

Examples of concrete implementations are ``ScalaCompilationUnit`` and ``ScriptCompilationUnit`` which is used in the `scala worksheet project <https://github.com/scala-ide/scala-worksheet>`_.

Tying it together
-----------------

So that was a quick tour of some of the most important classes when it comes to understanding the interaction between the IDE and the presentation compiler. However, seeing the classes and methods described independently still doesn’t give you the high-level understanding of how they interact so here is a small typical use-case:

::

    val compilationUnit = ScalaSourceFile.createFromPath(“A.scala”)
    compilationUnit.map { cu =>
      cu.withSourceFile { (source, pcompiler) =>
            pcompiler.withParseTree(source) { tree =>
          pcompiler.askOption { () =>
                  // Compute some value from the tree.
          }
        }
          } (/* default value if the presentation compiler isn’t initialized */)
    }.getOrElse(/* default value if it couldn’t load the file */)

First we get an ``Option[ScalaSourceFile]`` from the factory method ``createFromPath(path: String)``. ``ScalaSourceFile`` is a subclass of ``InteractiveCompilationUnit`` so we can invoke the method ``withSourceFile`` and hand it a closure that is given the current presentation compiler and the file associated with the compilation unit. We then hand the file to the presentation compiler and ask for the parsed tree and give it a closure that will be invoked once the tree has been generated. Once we have the tree we have to consider what we want to do with it: If we want to do anything that can cause side-effects to the tree we want to do it inside a call to ``askOption``, the reason for this is explained in the following section :ref:`risks`.

.. _risks:

Risks Related to the Presentation Compiler
------------------------------------------

The trees that the compiler works with have attributes that are lazy, that is, the initialization of some of the attributes of the trees is delayed until they're accessed. Furthermore some attributes of the nodes are mutated in the different phases of the compiler and the structure of the trees can be very different between the compiler phases. This means which phase to use depends on what information you want to retrieve. The trees are immutable but to avoid creating too many objects structural sharing is used when possible, that is, if a child of a node isn’t changed it will be reused in the newly created tree.

Consider the following Scala code:

::

    object A {
      @scala.annotation.tailrec
      def a(x: Int): Int = x
    }

Using ``scalac -Ybrowse:parser A.scala`` a GUI application is executed that can be used to browse the tree at a specific phase [#f1]_ (in this case the parser). If you compare the AST produced by the typer and parser phases we see that they’re quite different. For example, at the parser phase the owner of the method declaration isn’t defined whereas in the typer phase it is object A. Another interesting thing to notice is that the annotation no longer exists after the typer phase.

The presentation compiler runs in its own thread (``PresentationCompilerThread``) which is using thread confinement as its synchronization policy; this simply means that accessing the mutable state of the presentation compiler should only happen in the presentation compiler thread (i.e. the objects should be confined to that thread, hence the name). This is not guaranteed by the JVM so it is up to the developer to enforced this rule. This is prone to errors so the nightly version of the Scala IDE is built with a slightly modified version of the Scala compiler that contains a number of assertion checks that fail when a thread-unsafe attribute of a node is accessed outside the ``PresentationCompilerThread``; that way any potential error is more likely to be caught by this fail-fast approach.

Mixing the laziness of some of the attributes in the nodes of the tree and the synchronization policy of the ``PresentationCompilerThread`` causes some interesting concurrency issues that might not be immediately obvious. For example, lets say that some background thread in Eclipse asks for the parsed tree, then the presentation compiler thread will do some work and provide us with the tree, this is fine, but now in the background thread we start traversing and accessing attributes of the nodes; because some of these attributes are lazy we might cause them to be evaluated in our thread and hence have possible side effects on the internal state of the presentation compiler depending on how the attribute is initialized. So now, without meaning to, we have two threads accessing (and possibly mutating) the same non-thread-safe objects which is illegal with respect to the synchronization policy of the ``PresentationCompilerThread``. This is where askOption comes to the rescue. By using ``askOption`` a computation is forced to be executed in the presentation compiler thread so if any code that accesses the lazy attribute is wrapped in ``askedOpton`` it will be executing in the presentation compiler thread and we avoid the aforementioned problem. This however, can also be a double-edged sword because if you do any expensive computation inside askOption you are blocking the presentation compiler and therefore none of the other features that depend on the presentation compiler will work.

Another way to avoid the concurrency issue is to use a parsed tree rather than a typed one. The parse tree has the interesting property that it can be safely accessed outside of the presentation compiler thread because it doesn't have any attributes, so there is nothing that can be lazily evaluated, hence no mutation and side effects! This, however, is of course only possible if you don't actually need the information that is stored in the attributes. As mentioned you can get a parse tree by invoking the ``withParseTree`` method; at the time of writing this method is buggy and will sometimes return a typed tree, see the `ticket <https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001326#/activity/ticket>`_ for more information.

Examples of interactions between the IDE and the Presentation Compiler
----------------------------------------------------------------------

One example of the interaction between the Sala IDE and the presentation compiler is the semantic highlight.

Each editor (``ScalaSourceFileEditor``) has an associated ``InteractiveCompilationUnit``. Additionally each editor has something called a reconciler (description in this `blog post <http://blog.darevay.com/2007/11/the-eclipse-reconciler/>`_) that runs jobs in the background when the user takes a break from typing into the editor. ``SemanticHighlightingReconciliationParticipant`` is run during the reconciliation process but on a separate thread (see ``SemanticHighlightingJob``). This class simply forwards to the ``SemanticHighlightingReconciliation`` object which in turn will create an instance of ``SemanticHighlightingAnnotationsManager`` that has an inner class ``SemanticHighlightingJob(scu: ScalaCompilationUnit)`` which has a run method that looks (slightly modified) like this:

::

    def run(monitor: IProgressMonitor): IStatus = {
         scu.doWithSourceFile { (sourceFile, compiler) =>
           val useSyntacticHints = isUseSyntacticHintsEnabled
           val symbolInfos = try {
      SymbolClassifier.classifySymbols(sourceFile, compiler, useSyntacticHints)
           } catch {
             case e => Nil
           }
           if (!cancelled) setAnnotations(symbolInfos)
         }
         Status.OK_STATUS
        }

At some point the ``classifySymbols`` method invokes the ``compiler.loadedType(sourceFile)`` which will fully type check the tree, blocking until the presentation compiler has finished. When we have the type-checked tree it won’t change structure but we may still trigger side-effects by accessing some attribute of the tree, and as mentioned earlier any access to a attribute that can trigger side-effect in the compiler has to be executed inside an askOption call, or you may bring the Presentation Compiler in a corrupted state; if the presentation compiler is in a corrupted state, it may start to report errors that are incorrect (this is what we call ghost errors, they are false negative).

Resources
---------

* `Compiler internals videos <http://www.scala-lang.org/node/598>`_
* `Scala IDE presentation by Iulian <http://skillsmatter.com/podcast/scala/scala-ide-2-1>`_
* `Paul Philips' "Inside the Sausage Factory" at ScalaDays 2012 <http://skillsmatter.com/podcast/scala/scalac-internals>`_

Older Resources
---------------

* `Martin Odersky's talk to the SF Scala user group <http://www.youtube.com/watch?v=qqQNqIy5LdM&t=22m0s>`_
* `A general build walkthrough from the ENSIME blog <http://ensime.blogspot.ch/2010/08/building-ide-with-scala-presentation.html>`_

.. rubric:: Footnotes

.. [#f1] You can get a list of all the phases of the compiler by passing the -Yshow-phases option to scalac.
