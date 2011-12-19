Architecture
============

In this page you will learn about the Scala IDE's architecture. First, an overview of the 
different existing modules is provided. Then, a few specific aspects of the Scala IDE's architecture 
and its components are discussed.


Overview
--------

The Scala IDE project is composed of several modules. Here is a generic description of what each module contains:

* ``org.scala-ide.build``: Contains the scripts for building the Scala IDE from the command line.
* ``org.scala-ide.build-toolchain``: Contains the script for retrieving dependencies that are needed by the Scala IDE.
* ``org.scala-ide.sbt.full.library``: Groups together a bunch of SBT jars into one OSGi bundle.
* ``org.scala-ide.scala.compiler``: Wraps the Scala Compiler in a OSGi bundle (needed by the Eclipse runtime).
* ``org.scala-ide.scala.library``: Wraps the Scala Library in a OSGi bundle (needed by the Eclipse runtime).
* ``org.scala-ide.sdt.aspects``: Contains the AspectJ classes used to weave into Eclipse and hook in JDT internals.
* ``org.scala-ide.sdt.core``: Contains the Scala IDE plug-in's source code.
* ``org.scala-ide.sdt.core.tests``: Contains the functional tests used to exercise the Scala IDE in headless mode (with no User Interface).
* ``org.scala-ide.sdt.feature``: Packages the ``org.scala-ide.sdt.core`` project binaries into the "Scala IDE for Eclipse" component, which is then made available through the Scala IDE update site.
* ``org.scala-ide.sdt.source.feature``: Packages both the ``org.scala-ide.sdt.core`` and ``org.scala-ide.sdt.aspects`` project sources into the "Scala IDE for Eclipse Source Feature" component, which is then made available through the Scala IDE update site.
* ``org.scala-ide.sdt.weaving.feature``: Packages the ``org.scala-ide.sdt.aspects`` project binaries into the "JDT Weaving for Scala" component, which is included within the "Scala IDE for Eclipse" component.
* ``org.scala-ide.sdt.update-site``: Contains the result of a successful compilation of the Scala IDE project, packaged in the form expected by the Eclipse Update Manager.

Scala IDE & Eclipse JDT
-----------------------

The Scala IDE uses the `Eclipse Java Development Tools (JDT) <http://eclipse.org/jdt/>`_ weaving 
in order to inherit as much as possible of the existing Eclipse Java functionality. AspectJ 
and the weaving service of Eclipse (in fact, its OSGi container, called Equinox) allow us to 
intercept method calls in the JDT, and provide Scala-specific implementations for things like the 
`source file editor 
<https://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core/src/scala/tools/eclipse/ScalaSourceFileEditor.scala>`_ 
or the `indexer 
<https://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core/src/scala/tools/eclipse/javaelements/ScalaIndexBuilder.scala>`_.

By hooking into the JDT, and providing specific implementations of JDT interfaces, Scala entities
(classes, types, methods, etc) are seen by the JDT as Java elements, meaning:

* Java code completion “sees” Scala classes.
* The Java presentation compiler “sees” errors when using Scala classes.
* JDT tools, such as the JUnit runner, just ‘work’.
* Lots of UI elements can be reused (the Outline View, Package Explorer, etc).

Of course, Scala entities can not be faithfully represented in Java terms. Some Scala features will 
be therefore only partially supported when working in mixed Java-Scala projects (for instance, 
type members are represented as normal, value fields). 

.. note:: 

	This will hopefully change in the near future, as we plan to create our own models for 
	representing Scala entities and hook that into JDT (rather than the current solution, where we 
	are using Java models to expose Scala entities).
	
Although not intended to be extended with other languages, the JDT offers extension points for some 
functionality. Whenever possible, we prefer to use the regular, supported mechanism for extension, 
instead of weaving. This is the case for both code completion and hyperlinking.

JDT Weaving and Java integration
................................

All Scala aspect code is found in ``org.scala-ide.sdt.aspects`` project. This module defines:

* pointcuts and aspects to intercept JDT method calls,
* Java interfaces and classes that are used by these aspects. The main IDE module, ``org.scala-ide.sdt.core`` implements or inherits these classes. This way, there is a clean separation between the hooks, and the actual implementations.

The other side of this coin is the actual Scala-specific implementation in 
``org.scala-ide.sdt.core``. The package ``scala.tools.eclipse.javaelements`` contains Scala 
subclasses of Java element classes, such as ``ScalaClassElement`` or ``ScalaDefElement``.

Although the JDT defines interfaces for these elements, we inherit from their internal 
implementations. This makes our code prone to breakage in newer releases of the JDT, but gives us 
much more functionality. In some cases it is even impossible to do otherwise, as the JDT code itself 
casts such interfaces to their concrete, internal, implementations. 

.. note:: 

	As mentioned in the previous section, we plan to create our own models for representing Scala 
	entities. That will help us better representing Scala entities in the editor and it will 
	mitigate incompatibilities with newer version of the JDT.

The Scala Structure Builder
---------------------------

The workhorse of Java-Scala integration is the `ScalaStructureBuilder 
<http://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core/src/scala/tools/eclipse/javaelements/ScalaStructureBuilder.scala>`_ 
class. This class translates Scala sources (using the Abstract Syntax Trees - AST) to the JDT model elements (using the 
`Scala specific subclasses 
<http://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core/src/scala/tools/eclipse/javaelements/ScalaElements.scala>`_, 
of course), and it is fully responsible for the way the Eclipse Java compiler (and related JDT tools 
or UI elements) sees Scala code.

.. note:: 
	This approach has some important limitation with respect to correct interoperation of mixed 
	Scala/Java code, and it is the source of several issues in the Scala IDE. One major point of 
	pain is that the Scala Structure Builder needs to faithfully emulate the Scala compiler to 
	provide correct interoperation between Scala and Java code. Doing this in general is: 
	
	* difficult, as we are somewhat trying to re-engineer what the compiler already does when it produces bytecode, and 
	* fragile, as the produced bytecode may vary from one Scala version to another. 
	
	In the future we would like to explore other roads. For instance, we have been thinking about 
	using the binaries (instead of the sources), so that we would not need to duplicate the Scala 
	compiler backend's logic. Though, doing this would force users working on mixed Scala/Java projects 
	to enable continuous build (to make sure that the class binaries are actually produced), which 
	may not be acceptable.
	
The Scala Presentation Compiler
-------------------------------

In order to provide semantic actions, the IDE needs to *understand* the edited Scala code. That 
means parsing and type-checking. The `Scala Presentation Compiler 
<https://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core/src/scala/tools/eclipse/ScalaPresentationCompiler.scala>`_ 
is an asynchronous front-end compiler for Scala, part of the standard Scala compiler.

Code formatting
---------------

Code formatting is delegated to `Scalariform <https://github.com/mdr/scalariform/>`_, a library for 
automated Scala formatting written by Matt Russell. `Structured selection 
<https://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core/src/scala/tools/eclipse/ScalaStructureSelectEnclosingAction.scala>`_ 
and `tokenising 
<https://github.com/mdr/Scala-IDE/blob/f1a02cd3455aead4582a1652beddcc0b3dbd0f10/org.scala-ide.sdt.core/src/scala/tools/eclipse/lexical/ScalaCodeScanner.scala>`_ 
for syntax coloring are also backed by Scalariform.


Refactoring
------------

Refactoring is delegated to the `scala-refactoring <http://scala-refactoring.org/>`_ library written 
by Mirko Stocker.