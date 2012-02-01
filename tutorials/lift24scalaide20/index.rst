Setup and use Lift framework 2.4 in Scala IDE 2.0
=================================================

What is in this guide?
----------------------

This guide will show you how to configure a Lift web application to import it in Scala IDE, how to configure `Scala IDE`_ to work with the Lift framework and finally how to develop a Lift web application from inside Scala IDE.

Prerequisites
.............

*   `Eclipse`_ 3.7.1 (Indigo) with Scala IDE for Scala 2.9 installed (update site: http://download.scala-ide.org/releases-29/stable/site).

    Check the :ref:`getting started <gettingstarted_getting-started>` page for instructions on how to install Scala IDE.
    
*   `sbt`_ 0.11 installed.

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

*	The lift example projects still use sbt 0.7.5 but we will use the sbteclipse plugin to configure our project for Eclipse. As this plugin only works with sbt 0.10 and above we will replace the sbt configuration files coming with the ``lift_basic`` project with one compatible with sbt 0.11. To do so go to ``/path/to/lift_basic`` and delete the ``project`` folder as well as the files ``sbt-launcher.jar``, ``sbt`` and ``sbt.bat``

*	Create a file ``build.sbt`` with the following contents.

*	In a console go to ``/path/to/lift_basic`` and run ``sbt``.

*	Once sbt has successfully started run the ``update`` task to download all needed dependencies.

Configuring the Lift project for Scala IDE
------------------------------------------

To be able to import the project into Eclipse we are going to use the `sbteclipse`_ plugin for sbt to generate the Eclipse project files (.project, .classpath and optionally files in the .settings/ folder).

*	In your home folder create the folder structure ``.sbt/plugins``. In it create a file ``build.sbt`` with the following contents. For sbt version 0.11.2 (and higher) use the following. This will make the sbteclipse plugin globally available in all your sbt projects. You can also install the sbteclipse plugin only for this particular project; see the `sbteclipse wiki`_ for more information on how to install sbteclipse as a global vs a project local plugin.

*	In a console go to ``/path/to/lift_basic`` and run ``sbt``.

*	Once sbt has successfully started run the ``eclipse with-sources`` task to generate the Eclipse project files.

Importing the Lift project into Scala IDE
-----------------------------------------

Everything is setup, it is time to import the project into the IDE.

*	Import the Lift project as an ``Existing Project`` into your Workspace (``File --> Import --> Existing Projects into Workspace``).

Running and Debugging the Lift project in Scala IDE
---------------------------------------------------

The ``lift_basic`` project includes the RunWebApp Application in ``src/test/scala`` which bootstraps the embedded Jetty server.

*	Right click ``RunWebApp.scala`` in Eclipse and select ``Run As --> Scala Application`` (or ``Debug As --> Scala Application``).

*	Open a web browser and go to http://localhost:8080 to see your running Lift application.

Optional: enabling JRebel support for the Lift project in Scala IDE
-------------------------------------------------------------------

To enable JRebel support for your Lift project in Scala IDE you have to install the `JRebel Eclipse Plugin`_. For more information on JRebel see `JRebel website`_.

*	Once the JRebel plugin is installed and configured open the Run Configuration for ``RunWebApp.scala``, switch to the ``JRebel`` tab and activate JRebel.

*	Now when running or debugging ``RunWebApp.scala`` JRebel will pick up your code changes and automatically replace them in the running application without the need for redeploying or restarting.

Going further
-------------

You now have all you need to create great web applications with Lift 2.4 and Scala.

For more information about Lift, check out the `Lift wiki`_.

For more information about Scala, go to the `documentation website`_ or get the downloadable `eBook`_.

For more information about sbt, go to the `sbt wiki`_.

Feedback
--------

This guide is managed through in the `Scala IDE documentation project`_ on github.
Please use github ticket and pull request system for feedback.


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
