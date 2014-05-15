.. Copyright (c) 2014 Contributor. All rights reserved.

.. include:: /global_defs.hrst

Custom semantic annotations |new|
=================================

You can add your own semantic highlighters to Scala IDE via small eclipse plugins.

Mechanism itself uses existing extension (one for implicit highlighters) and provides you with some basic scaffolding
that allows easy plugin creation.

Scaffolding
-----------

Main classes provided for this are instances of ``TraverserDef`` which provides a way to search for certain pieces of code:

* ``AllMethodsTraverserDef`` - searches for all method calls on given type (and it's subtypes)
* ``MethodTraverserDef`` - searches for all method calls of given method
* ``AnnotationTrverserDef`` - searches for all usages of methods annotated with given annotation

(all above by 'method' understands ``val``\s, ``var``\s, ``def``\s and/or ``lazy val``\s)

Those are then used to create ``CustomSemanticAction`` and ``SemanticHighlightingParticipant``
(last one is plugged into Scala IDE via extension).

Example
-------

For example to highlight all method usages on Scala mutable collections one has to create
a plugin containing a single classes inside that defines your traversers and highlighter::

    package annotations.demo

    import org.eclipse.jface.text.source.ISourceViewer
    import org.scalaide.core.extensions.SemanticHighlightingParticipant
    import org.scalaide.ui.internal.editor.decorators.SemanticAction
    import org.scalaide.ui.internal.editor.decorators.custom.AllMethodsTraverserDef
    import org.scalaide.ui.internal.editor.decorators.custom.CustomSemanticAction
    import org.scalaide.ui.internal.editor.decorators.custom.TraverserDef.TypeDefinition

    /**
     * Object with some common properties used by this plugin.
     */
    object MutableCollectionCallHighlightingParticipant {

      /** Id of annotation, used in plugin.xml */
      lazy val annotationId = "annotations.demo.mutable"

      /** Traverser that looks for all method calls on children of collection.mutable.Traversable */
      lazy val traversers = Seq(
        AllMethodsTraverserDef(
          message = name => s"Method call on a mutable collection: '$name'",
          typeDefinition = TypeDefinition("scala" :: "collection" :: "mutable" :: Nil, "Traversable")))

      /** Highlighting actions */
      def action(viewer: ISourceViewer): SemanticAction =
        new CustomSemanticAction(traversers, viewer, annotationId)
    }

    /**
     * Custom highlighter for mutable collection methods.
     *
     * It's plugged to Scala IDE extension (see plugin.xml).
     */
    class MutableCollectionCallHighlightingParticipant
      extends SemanticHighlightingParticipant(MutableCollectionCallHighlightingParticipant.action)


And plug them into Scala IDE

in ``plugin.xml``::

    <?xml version="1.0" encoding="UTF-8"?>
    <?eclipse version="3.4"?>
    <plugin>
        <extension point="org.eclipse.ui.editors.annotationTypes">
            <type name="annotations.demo.mutable" />
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

        <extension point="org.scala-ide.sdt.core.semanticHighlightingParticipants">
            <participant
                class="annotations.demo.MutableCollectionCallHighlightingParticipant">
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