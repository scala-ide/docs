.. Copyright (c) 2014 Contributor. All rights reserved. This program and the accompanying materials
   are made available under the terms of the Scala License which accompanies this distribution, and
   is available at http://www.scala-lang.org/node/146

.. include:: /global_defs.hrst

Extending Scala IDE |new|
=========================

Custom semantic annotations
---------------------------

You can add your own semantic highlighters to Scala IDE via small eclipse plugins.

Mechanism itself uses existing extension (one for implicit highlighters) and provides you with some basic scaffolding that allows easy plugin creation.

Scaffolding
~~~~~~~~~~~

Main classes provided for this are instances of ``TraverserDef`` which provides a way to search for certain pieces of code:

* ``AllMethodsTraverserDef`` - searches for all method calls on given type (and it's subtypes)
* ``MethodTraverserDef`` - searches for all method calls of given method
* ``AnnotationTrverserDef`` - searches for all usages of methods annotated with given annotation

(all above by 'method' understands ``val``\s, ``var``\s, ``def``\s and/or ``lazy val``\s)

Those are then used to create ``CustomSemanticAction``, ``SemanticHighlightingReconciliation``, and ``SemanticHighlightingReconciliationParticipant`` (last one is plugged into Scala IDE).

Example
~~~~~~~

For example to highlight all method usages on Scala mutable collections one has to create
a plugin containing a single classes inside that defines your traversers and highlighter::

    package my.company.highlighers.mutable

    import org.eclipse.jdt.internal.ui.javaeditor.JavaSourceViewer
    import org.scalaide.ui.internal.editor.decorators.custom.AllMethodsTraverserDef
    import org.scalaide.ui.internal.editor.decorators.custom.CustomSemanticAction
    import org.scalaide.ui.internal.editor.decorators.custom.TraverserDef.TypeDefinition
    import org.scalaide.ui.internal.editor.decorators.semantichighlighting.SemanticHighlightingReconciliation
    import org.scalaide.ui.internal.editor.decorators.semantichighlighting.SemanticHighlightingReconciliationParticipant
    import org.scalaide.ui.internal.editor.decorators.SemanticAction

    object MutableCollectionHighlighter {

      /** Id of annotation, used in plugin.xml */
      lazy val annotationId = "annotations.demo.mutable"

      /** Traverser that looks for all method calls on children of collection.mutable.Traversable */
      lazy val traversers = Seq(
        AllMethodsTraverserDef(
          message = "Method call on a mutable collection.",
          typeDefinition = TypeDefinition("scala" :: "collection" :: "mutable" :: Nil, "Traversable")))


      /** Highlighting actions */
      lazy val actions: List[JavaSourceViewer => SemanticAction] =
        List(viewer => new CustomSemanticAction(traversers, viewer, annotationId))
    }

    /**
     * Custom highlihter for mutable collection methods.
     *
     * It's plugged to Scala IDE extension (see plugin.xml).
     */
    class MutableCollectionHighlighter extends SemanticHighlightingReconciliationParticipant(
      reconciler = new SemanticHighlightingReconciliation(actions))


And plug them into Scala IDE

in ``plugin.xml``::

    <?xml version="1.0" encoding="UTF-8"?>
    <?eclipse version="3.4"?>
    <plugin>
      <extension point="org.eclipse.ui.editors.annotationTypes">
         <type name="annotations.demo.mutable"/>
      </extension>

      <extension point="org.eclipse.ui.editors.markerAnnotationSpecification">
        <specification
          annotationType="annotations.demo.mutable"
          colorPreferenceKey="scala.tools.eclipse.ui.preferences.mutable.color"
          colorPreferenceValue="224,25,25"
          contributesToHeader="false"
          includeOnPreferencePage="true"
          icon="icons/mutable.gif"
          label="Mutable collection calls"
          overviewRulerPreferenceKey="scala.tools.eclipse.ui.preferences.mutable.rulers.overview"
          overviewRulerPreferenceValue="false"
          presentationLayer="5"
          symbolicIcon="info"
          textPreferenceKey="scala.tools.eclipse.ui.preferences.mutable.text"
          textPreferenceValue="true"
          textStylePreferenceKey="scala.tools.eclipse.ui.preferences.mutable.textStylePreference"
          textStylePreferenceValue="SQUIGGLES"
          verticalRulerPreferenceKey="scala.tools.eclipse.ui.preferences.mutable.rulers.vertical"
          verticalRulerPreferenceValue="true">
        </specification>
      </extension>

      <extension point="org.scala-ide.sdt.core.reconciliationParticipants">
        <participant
          class="annotations.demo.MutableCollectionHighlighter"
          id="annotations.demo.MutableCollectionHighlighter"
          name="Mutable collections calls highlighting">
        </participant>
      </extension>
    </plugin>

in ``MANIFEST.MF``::

    Manifest-Version: 1.0
    Bundle-ManifestVersion: 2
    Bundle-Name: AnnotationsDemo
    Bundle-SymbolicName: annotations.demo;singleton:=true
    Bundle-Version: 1.0.0.qualifier
    Bundle-Activator: annotations.demo.Activator
    Require-Bundle: org.eclipse.core.resources,
     org.eclipse.core.runtime,
     org.eclipse.jdt.core,
     org.eclipse.jdt.core.manipulation,
     org.eclipse.jdt.ui,
     org.eclipse.jface.text,
     org.eclipse.ui,
     org.eclipse.ui.browser,
     org.eclipse.ui.editors,
     org.eclipse.ui.workbench.texteditor,
     org.scala-lang.scala-library,
     org.scala-lang.scala-reflect,
     org.scala-lang.scala-compiler,
     org.scala-ide.sdt.core,
     org.scala-ide.sbt.compiler.interface,
     org.scala-ide.sbt.full.library
    Import-Package:
     scala.tools.eclipse.contribution.weaving.jdt;apply-aspects:=false
    Bundle-RequiredExecutionEnvironment: JavaSE-1.6
    Bundle-ActivationPolicy: lazy

For full example project see `Demo project <https://github.com/VirtusLab/scala-ide-annotations-demo>`_.