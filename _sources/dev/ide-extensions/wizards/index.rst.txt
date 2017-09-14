.. include:: /global_defs.hrst

Wizards |new|
=============

It is possible to add entries to the file kind option of the wizard. To do so one has to provide an OSGi bundle.

In the following there is a description on how to create such an OSGi bundle:

1. Create a new Plug-in Project in Eclipse and add the Scala nature.
2. Add the Scala IDE bundle as a dependency in the dependencies section of your manifest file. This bundle is called ``org.scala-ide.sdt.core`` and its minimum version needs to be 4.0.0. Because the public API of the wizard makes use of Eclipse based classes, you also have to add ``org.eclipse.core.resources`` and ``org.eclipse.core.runtime`` as dependencies.
3. In the extensions section add the extension point ``org.scala-ide.sdt.core.fileCreator``.
4. Create a new ``fileCreator``.
5. In order to fill out all values of the ``fileCreator`` you have to provide a class that implements ``org.scalaide.ui.wizards.FileCreator``. See the Scaladoc of this trait to find out how to implement it correctly.
6. Once you are done with implementing the class, you have to provide a template, which is later used by the wizard to create the contents of a newly created file. To do so, first add a new dependency to ``org.eclipse.ui.editors`` and then add the extension point ``org.eclipse.ui.editors.templates``.
7. Create a ``template``, whose ``contextTypeId`` must be ``org.scala-ide.sdt.core.templates``. Fill in the template ID into the corresponding field in your ``fileCreator``.

You can now export a bundle and install it to your Eclipse installation. If you made everything correctly, the new wizard should show your template in the kind field.

In the following we show you an example bundle manifest and plugin configuration:

``plugin.xml``::

  <?xml version="1.0" encoding="UTF-8"?>
  <?eclipse version="3.4"?>
  <plugin>
     <extension
           point="org.scala-ide.sdt.core.fileCreator">
        <fileCreator
              class="org.scalaide.wizardextension.TestFileCreator"
              description="test dcription"
              id="org.scalaide.wizardextension.id"
              name="test name"
              templateId="org.scalaide.wizardextension.template">
        </fileCreator>
     </extension>
     <extension
           point="org.eclipse.ui.editors.templates">
        <template
              autoinsert="true"
              contextTypeId="org.scala-ide.sdt.core.templates"
              id="org.scalaide.wizardextension.template"
              name="wizard_test">
           <pattern>
              My test content
           </pattern>
        </template>
     </extension>
  </plugin>

``MANIFEST.MF``::

  Manifest-Version: 1.0
  Bundle-ManifestVersion: 2
  Bundle-Name: Wizardextension
  Bundle-SymbolicName: org.scalaide.wizardextension;singleton:=true
  Bundle-Version: 1.0.0.qualifier
  Bundle-RequiredExecutionEnvironment: JavaSE-1.7
  Require-Bundle: org.scala-ide.sdt.core;bundle-version="4.0.0",
   org.scala-lang.scala-library,
   org.eclipse.core.resources;bundle-version="3.8.101",
   org.eclipse.core.runtime;bundle-version="3.9.100",
   org.eclipse.ui.editors;bundle-version="3.8.100"

