Getting Started
===============

Requirements
------------

* the Java Development Kit, version 1.5.0 or newer. We recommend the latest 1.6.x version, there have been issues reported when using Eclipse and Java 7.

* Eclipse, including the JDT. Either the "Classic" or "Eclipse for Java Developers" is sufficient.

  * Scala IDE is officially supporting Eclipse 3.6.2.

  * Scala IDE can be installed on Eclipse 3.7.1, but not all features are working perfectly. For example, discovery of unit tests is not working (ticket `1000782`__).

__ https://www.assembla.com/spaces/scala-ide/tickets/1000782

Installation
------------

Scala IDE for Eclipse is best installed (and updated) directly from within Eclipse.

This is done by using ``Help → Install New Software...``, add the ``Add...`` button in the dialog.

Choose a name for the update site (`Scala IDE` is an obvious choice). Then read the next section to select which version you will install.

Choosing what version to install
................................

`download.scala-ide.org`__ provides the URLs of all available update sites. The release ones are in the first section. Scala IDE is linked to specific version of Scala, so you have to decide which one you are going to use:

* **release-29** provides support for projects using Scala 2.9.x (2.9.0-1 or 2.9.1). *This is the current version of Scala. Pick this one if you are unsure*.

* **release-28** provides support for projects using Scala 2.8.x (2.8.1 or 2.8.2).

If you want to live on the bleeding edge (like using Scala 2.10.x or Scala IDE master), check the description of each update site to find the one that fits your needs.

The version of Scala used inside Scala IDE cannot be chosen per project. So if you want to work with project using different version of Scala (like 2.8.2 and 2.9.1), you need different installation of Scala IDE.

__ http://download.scala-ide.org

Finishing installation
......................

Copy the URL as location and hit ``OK`` to validate.

Select ``Scala IDE for Eclipse`` from the list of available features.

Go through the next screens to validate the list of plug-ins to install, the :doc:`../license` and start the installation.

After having restarted Eclipse, Scala IDE is installed.

For a more fine-tuned setup, check the :doc:`Advanced Setup <advancedsetup>` page.

Using Scala IDE with an existing project
----------------------------------------

You have successfully installed the Scala plug-in, and you can now import your favorite project.

.. important:: Before you begin, make sure your Scala sources follow the Java convention for packages.
   Each package should appear in its own directory with the same name. Eclipse expects this convention to be followed when it looks for classes.

Start from an existing SBT project
..................................

`sbt`_ is the standard build tool for Scala projects. There are two versions in wide use today: 0.7.x and 0.10+. Both have plug-ins that can generate Eclipse project files out of the sbt project definition.

* If you are using **sbt 0.7.x**, use `eclipsify`_. Follow this `instructions`__ to install the plug-in. You may want to check out the update-sources plug-in, which can download attached sources for your dependencies. This allows you to navigate through the libraries source in the IDE.

* If you are using **sbt 0.11.x** use `sbteclipse`_. Follow the instructions on the project page to install and use the plug-in to generate the project definition. sbteclipse can download attached sources natively, so you do not need the update-sources plug-in.

Once you have installed and generated the Eclipse project files using one of the above plug-ins, start Eclipse. Use ``File → Import → General/Existing Project into Workspace``. Select the directory containing your project as ``root directory``, select the project and hit ``Finish``. And *Voila*.

.. important:: sbt manages the dependency to scala-library.jar. Make sure the version of Scala you use in your sbt project matches the version of Scala installed in Eclipse.

__ https://github.com/musk/SbtEclipsify/tree/0.8.0

Start from an existing maven project
....................................

`maven`_ support on the Eclipse platform is provided by the `m2eclipse`_ plug-in. Because of some specificities of Scala, `m2eclipse-scala`_ has been created as an extension to m2eclipse to provide better Scala support.

To install the latest version of m2eclipse-scala, use the update site at this location: http://alchim31.free.fr/m2e-scala/update-site/.

After installation, maven projects can be imported using ``File → Import → Maven → Existing Maven Projects``.

Troubleshooting
---------------

Check the :ref:`faq_known-issues` section.


.. _eclipsify: https://github.com/musk/SbtEclipsify/tree/0.8.0
.. _m2eclipse: http://www.eclipse.org/m2e/
.. _m2eclipse-scala: https://github.com/sonatype/m2eclipse-scala
.. _maven: http://maven.apache.org/
.. _sbt: http://www.scala-sbt.org/
.. _sbteclipse: https://github.com/typesafehub/sbteclipse
