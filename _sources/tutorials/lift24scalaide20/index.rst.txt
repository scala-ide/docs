Setup and use Lift framework 2.4 in Scala IDE 2.0
=================================================

What is in this guide?
----------------------

This guide will show you how to configure a Lift web application to import it in Scala IDE, how to configure `Scala IDE`_ to work with the Lift framework and finally how to develop a Lift web application from inside Scala IDE.

Prerequisites
.............

*   `Eclipse`_ 3.7.2 (Indigo) with Scala IDE for Scala 2.9 installed (update site: http://download.scala-ide.org/releases-29/stable/site).

    Check the :doc:`getting started </current-user-doc/gettingstarted/index>` page for instructions on how to install Scala IDE.

*   Simple Build Tool `sbt`_ 0.11.2 installed.

    Check the `sbt Getting Started Guide`_ for instructions on how to install sbt.

*   Basic knowledge of the Eclipse user interface is required (in this guide).

*   Basic knowledge of the Scala language is required (in this guide).

*   Basic knowledge of the Simple Build Tool (sbt) is required (in this guide).

*   Basic knowledge of the Lift framework is required (in this guide).

Setting up a Lift project
-------------------------

Start by creating a basic Lift project

*	Download the Lift 2.4 release from http://liftweb.net/download

*	Unzip it and copy the folder ``scala_29/lift_basic`` to your preferred location. Let’s say ``/path/to/lift_basic`` for the purpose of this document.

*	The lift example projects still use sbt 0.7.5 but we will use the sbteclipse plugin to configure our project for Eclipse. As this plugin only works with sbt 0.10 and above we will replace the sbt configuration files in the ``lift_basic`` project with one compatible with sbt 0.11.2. To do so go to ``/path/to/lift_basic`` and delete everything except the ``src`` folder. Your ``/path/to/lift_basic`` folder should now look like this:

    .. image:: images/01-lift_basic_folder_structure.png
       :alt: lift-basic folder structure
       :target: ../../_images/01-lift_basic_folder_structure.png

*	Create a file ``build.sbt`` with the following contents:

    .. code-block:: scala

         name := "lift-basic"

         organization := "my.company"

         version := "0.1-SNAPSHOT"

         scalaVersion := "2.9.1"

         libraryDependencies ++= {
         	val liftVersion = "2.4"
         	Seq(
         		"net.liftweb" %% "lift-webkit" % liftVersion % "compile",
         		"net.liftweb" %% "lift-mapper" % liftVersion % "compile",
         		"org.mortbay.jetty" % "jetty" % "6.1.26" % "test",
         		"junit" % "junit" % "4.7" % "test",
         		"ch.qos.logback" % "logback-classic" % "0.9.26",
         		"org.scala-tools.testing" %% "specs" % "1.6.9" % "test",
         		"com.h2database" % "h2" % "1.2.147"
         	)
         }

*	In a console go to ``/path/to/lift_basic`` and run ``sbt``.

*	Once sbt has successfully started run the ``update`` task to download all needed dependencies.

Configuring the Lift project for Scala IDE
------------------------------------------

To be able to import the project into Eclipse we are going to use the `sbteclipse`_ plugin for sbt to generate the Eclipse project files (.project, .classpath, etc.).

*	In your home folder create the file ``.sbt/plugins/build.sbt`` with the following contents:

	.. code-block:: scala

		addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.0.0")

	This will make the sbteclipse plugin globally available in all your sbt projects. You can also install the sbteclipse plugin only for a particular project; see the `sbteclipse wiki`_ for more information on how to install sbteclipse as a global vs. a project local plugin.

* 	Open ``/path/to/lift_basic/build.sbt`` and add the following line:

	.. code-block:: scala

		EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

	Your ``/path/to/lift_basic/build.sbt`` should now look like this:

    .. code-block:: scala

         name := "lift-basic"

         organization := "my.company"

         version := "0.1-SNAPSHOT"

         scalaVersion := "2.9.1"

         EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

         libraryDependencies ++= {
         	val liftVersion = "2.4"
         	Seq(
         		"net.liftweb" %% "lift-webkit" % liftVersion % "compile",
         		"net.liftweb" %% "lift-mapper" % liftVersion % "compile",
         		"org.mortbay.jetty" % "jetty" % "6.1.26" % "test",
         		"junit" % "junit" % "4.7" % "test",
         		"ch.qos.logback" % "logback-classic" % "0.9.26",
         		"org.scala-tools.testing" %% "specs" % "1.6.9" % "test",
         		"com.h2database" % "h2" % "1.2.147"
         	)
         }

*	In a console go to ``/path/to/lift_basic`` and run ``sbt``.

*	Once sbt has successfully started run the ``eclipse`` task to generate Eclipse project files. Optionally you can run ``eclipse with-source=true`` to tell sbteclipse to try to download source and doc artifacts and create Eclipse source and doc attachments for library dependencies.

Importing the Lift project into Scala IDE
-----------------------------------------

Everything is setup, it is time to import the project into Eclipse.

*	Import the Lift project as an ``Existing Project`` into your Workspace (``File --> Import --> Existing Projects into Workspace``). Set ``/path/to/lift_basic`` as the root directory and click ``Finish``.

    .. image:: images/02-import_project_dialog.png
       :alt: eclipes import project dialog
       :target: ../../_images/02-import_project_dialog.png

*	Your Eclipse project structure should now look like this:

    .. image:: images/03-eclipse_project_structure.png
       :alt: eclipes project structure
       :target: ../../_images/03-eclipse_project_structure.png

Running and Debugging the Lift project in Scala IDE
---------------------------------------------------

The ``lift_basic`` project includes the RunWebApp Application in ``src/test/scala`` which bootstraps the embedded Jetty server.

*	Right click ``RunWebApp.scala`` in Eclipse and select ``Run As --> Scala Application`` (or ``Debug As --> Scala Application``).

*	Open a web browser and go to http://localhost:8080 to see your running Lift application.

Optional: enabling JRebel support for the Lift project in Scala IDE
-------------------------------------------------------------------

To enable JRebel support for your Lift project in Scala IDE you have to install the `JRebel Eclipse Plugin`_. For more information on JRebel see `JRebel website`_.

*	Once the JRebel plugin is installed and configured open the Run Configuration for ``RunWebApp.scala`` (Right click ``RunWebApp.scala`` in Eclipse and select ``Run As --> Run Configurations...``). Switch to the ``JRebel`` tab and activate JRebel:

    .. image:: images/04-eclipse_run_configuration_jrebel.png
       :alt: eclipse run configuration jrebel tab
       :target: ../../_images/04-eclipse_run_configuration_jrebel.png

*	Now when debugging ``RunWebApp.scala`` JRebel will pick up your code changes and automatically replace them in the running application without the need for redeploying or restarting.

Going further
-------------

You now have all you need to create web applications with Lift 2.4 and Scala in Eclipse.

For more information about Lift, check out the `Lift wiki`_.

For more information about Scala, go to the `documentation website`_ or get the downloadable `eBook`_.

For more information about sbt, go to the `sbt wiki`_.

Feedback
--------

This guide is managed through in the `Scala IDE documentation project`_ on github.
Please use github ticket and pull request system for feedback.

heapifyman


.. _Scala IDE: http://www.scala-ide.org
.. _Scala IDE documentation project: https://github.com/scala-ide/docs
.. _documentation website: http://docs.scala-lang.org/
.. _Eclipse: http://www.eclipse.org/
.. _Lift wiki: http://www.assembla.com/wiki/show/liftweb
.. _sbt: https://github.com/harrah/xsbt
.. _sbt wiki: https://github.com/harrah/xsbt/wiki
.. _sbt Getting Started Guide: https://github.com/harrah/xsbt/wiki/Getting-Started-Welcome
.. _sbteclipse: https://github.com/typesafehub/sbteclipse
.. _sbteclipse wiki: https://github.com/typesafehub/sbteclipse/wiki/Installing-sbteclipse
.. _JRebel website: http://zeroturnaround.com/jrebel/
.. _JRebel Eclipse Plugin: http://zeroturnaround.com/jrebel/installing-jrebel-plugin-for-eclipse/
.. _eBook: http://typesafe.com/resources/scala-for-the-impatient
