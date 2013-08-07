Getting Started
===============

Requirements
------------

- The Java Development Kit, version 1.6.0 or newer.

- Eclipse, including the JDT. Either the "Classic" or "Eclipse for Java Developers" is sufficient.  Scala IDE v3.0 can be installed on both `Eclipse 3.7 (Indigo)`_ and `Eclipse 4.2 (Juno)`_, through different update sites.

    - If you are using `Eclipse 3.7 (Indigo)`_, we recommend the latest 1.6.x version, *because there have been issues reported when used with Java 7* (e.g., `[1]`_, or `[2]`_  if you are on MacOSX - a workaround for the latter issue is described `here`_).

    - `Eclipse 4.2 (Juno)`_ has full support for Java 7. We recommend to use the latest Eclipse 4.2 SR2 release which contains several performance improvements.

.. _[1]: https://bugs.eclipse.org/bugs/show_bug.cgi?id=381612
.. _[2]: https://bugs.eclipse.org/bugs/show_bug.cgi?id=374791
.. _here: http://stackoverflow.com/questions/10352715/how-do-i-run-eclipse-using-oracles-new-1-7-jdk-for-the-mac

Screencast
----------

The getting started screencast provides a more visual explanation on the installation and the first steps with Scala IDE.

.. raw:: html

   <div id="getting_started_video" class="doc_video">
     <img class="doc_video" src="../../_images/splash-getting-started-video.png" onclick="startVideo('getting_started_video', 'dGI04efb6hg')"/>
   </div>

Installation
------------

Scala IDE for Eclipse is best installed (and updated) directly from within Eclipse.

This is done by using ``Help → Install New Software...``, add the ``Add...`` button in the dialog.

Choose a name for the update site (`Scala IDE` is an obvious choice). Then read the next section to select which version you will install.

Choosing what version to install
................................

The list of URLs of the different update sites are available in the download area. The release ones are in the `current section`_. Scala IDE is linked to specific version of Scala, so you have to decide which one you are going to use:

* **For Scala 2.10**: provides support for projects using Scala 2.10 (any minor version).  *This is the current version of Scala. Pick this one if you are unsure*.

* **For Scala 2.9**: provides support for projects using Scala 2.9 (any minor version).

If you want to live on the bleeding edge (i.e., using the development stream for Scala and Scala IDE), check the `nightlies section`_ to find the one that fits your needs.

**The version of Scala used inside Scala IDE cannot be chosen per project. So, if you want to work with a project using different version of Scala (like 2.9.3 and 2.10.1), you need different installation of Scala IDE.**

Finishing installation
......................

Copy the URL as location and hit ``OK`` to validate.

Select ``Scala IDE for Eclipse`` from the list of available features.

If you want to install any additional plug-ins (this step is optional), expand the ``Scala IDE plugins`` category and select the plug-ins that fits you best.

Go through the next screens to validate the list of plug-ins to install, the :doc:`/license` and start the installation.

After having restarted Eclipse, Scala IDE is installed.

For a more fine-tuned setup, check the :doc:`Advanced Setup </current-user-doc/advancedsetup/index>` page.


Using Scala IDE with an existing project
----------------------------------------

You have successfully installed the Scala plug-in, and you can now import your favorite project.

.. important:: Before you begin, make sure your Scala sources follow the Java convention for packages.
   Each package should appear in its own directory with the same name. Eclipse expects this convention to be followed when it looks for classes.

Import an SBT project
.....................

`sbt`_ is the standard build tool for Scala projects. There are two versions in wide use today: 0.7.x and 0.10+. Both have plug-ins that can generate Eclipse project files out of the sbt project definition.

* If you are using **sbt 0.7.x**, use `eclipsify`_. Follow this `instructions`__ to install the plug-in. You may want to check out the update-sources plug-in, which can download attached sources for your dependencies. This allows you to navigate through the libraries source in the IDE.

* If you are using **sbt 0.12.x**, use `sbteclipse`_. Follow the instructions on the project page to install and use the plug-in to generate the project definition. sbteclipse can download attached sources natively, so you do not need the update-sources plug-in.

Once you have installed and generated the Eclipse project files using one of the above plug-ins, start Eclipse. Use ``File → Import → General/Existing Project into Workspace``. Select the directory containing your project as ``root directory``, select the project and hit ``Finish``. And *Voila*.

.. important:: sbt manages the dependency to scala-library.jar. Make sure the version of Scala you use in your sbt project matches the version of Scala installed in Eclipse.

__ https://github.com/musk/SbtEclipsify/tree/0.8.0

Import a Maven project
......................

`maven`_ support on the Eclipse platform is provided by the `m2eclipse`_ plug-in. Because of some specificities of Scala, `m2eclipse-scala`_ has been created as an extension to m2eclipse to provide better Scala support.

To install the latest version of m2eclipse-scala, use the update site at this location: http://alchim31.free.fr/m2e-scala/update-site/.

After installation, maven projects can be imported using ``File → Import → Maven → Existing Maven Projects``.

Troubleshooting
---------------

Check the :doc:`Known Issues </current-user-doc/faq/known-issues>` section.


.. _current section: http://scala-ide.org/download/current.html
.. _nightlies section: http://scala-ide.org/download/nightly.html
.. _eclipsify: https://github.com/musk/SbtEclipsify/tree/0.8.0
.. _Eclipse 3.7 (Indigo): http://www.eclipse.org/downloads/packages/eclipse-classic-372/indigosr2
.. _Eclipse 4.2 (Juno): http://www.eclipse.org/downloads/packages/release/juno/sr2
.. _m2eclipse: http://www.eclipse.org/m2e/
.. _m2eclipse-scala: https://github.com/sonatype/m2eclipse-scala
.. _maven: http://maven.apache.org/
.. _sbt: http://www.scala-sbt.org/
.. _sbteclipse: https://github.com/typesafehub/sbteclipse
