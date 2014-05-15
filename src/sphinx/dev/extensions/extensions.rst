.. Copyright (c) 2014 Contributor. All rights reserved. This program and the accompanying materials
   are made available under the terms of the Scala License which accompanies this distribution, and
   is available at http://www.scala-lang.org/node/146

Extending Scala IDE
===================

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

(all above by 'method' understands ``val``s, ``var``s, ``def``s and/or ``lazy val``s)

Those are then used to create ``CustomSemanticAction``, ``SemanticHighlightingReconciliation``, and ``SemanticHighlightingReconciliationParticipant`` (last one is plugged into Scala IDE).

Example
~~~~~~~

For example to highlight all method usages on Scala mutable collections one has to create
a plugin with a single classes inside that define your traversers and highlihter::

    package my.company.highlighers.mutable

    object MutableCollectionHighlighter {

      /** Id of annotation, used in plugin.xml */
      lazy val annotationId = "annotations.demo.mutable"

      /** Traverser that looks for all method calls on children of collection.mutable.Traversable */
      lazy val traversers = Seq(
        AllMethodsTraverserDefinition(
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


And plug them into Scala IDE (in ``plugin.xml``)::

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

