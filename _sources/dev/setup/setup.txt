Setup
=====

In this page you will learn everything you need to know to setup the Scala IDE project in Eclipse.
You are literally instants away from being all set and ready to start hacking the Scala IDE sources!

Requirements
------------

* `JDK 5 <http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase5-419410.html>`_
  or `JDK 6 <http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase6-419409.html>`_
  (JDK 7 is **not** supported. As a matter of fact, there have been issues reported when running Eclipse with a Java 7 JVM. For instance,
  see https://bugs.eclipse.org/bugs/show_bug.cgi?id=364735).

* `Maven 3 <http://maven.apache.org/download.html>`_.

* `Git <http://git-scm.com/>`_ and a `GitHub <https://github.com/>`_ account.

* `Eclipse 3.7 (Indigo) <http://www.eclipse.org/downloads/>`_, including the JDT. Either "Eclipse Classic" or "Eclipse for Java Developers" is sufficient (Be aware, the plugin's sources are no longer compatible with Eclipse 3.6).

* The `Scala IDE Helium for Scala 2.10 <http://scala-ide.org/download/nightly.html>`_.

This document uses a script to build Scala IDE. If you are on Windows, we suggest you to install Cygwin (how could you live without it on Windows anyway!?). Otherwise, the :ref:`building_run-the-build` section of the documentation describes the different steps to execute for a full build.

.. _setup_fork-the-project:

Fork the project
----------------

The first thing you should do is forking the `Scala IDE repository <http://github.com/scala-ide/scala-ide>`_,
that will greatly simplify the process of sending us patches (remember to also read about the
:ref:`workflow_page` before creating pull requests).

If you are new to GitHub, `read here to learn how to fork a project
<http://help.github.com/fork-a-repo/>`_.

After forking the project, simply open a terminal and clone your own fork to download the project's
sources. The command for cloning the fork should be very close to the following one (mind that
you will have to replace *<username>* with your actual Git username).

.. code-block:: bash

        $ git clone git@github.com:<username>/scala-ide.git

Make sure to `add an Upstream <http://help.github.com/fork-a-repo/#Set-Up-Your-Local-Repo>`_ pointing
to the original Scala IDE git remote repository, so that you can keep fetching the latest changes
made in the project, and easily integrate them back in your fork.

Pull the libraries
------------------

The Scala IDE projects uses libraries which are pulled during the build process and copied in the ``target/lib`` folders.
So let's run the build.

From the project root, run the following command if you are using Scala IDE with Scala 2.10:

.. code-block:: bash

   $ ./build-all.sh clean install

or the following if you are using Scala IDE with Scala 2.9:

.. code-block:: bash

   $ ./build-all.sh -P scala-2.9.x clean install

If you want more information concerning the build, check out :ref:`building_run-the-build`.

Import the projects into Eclipse
--------------------------------

The Scala IDE project already contains the metadata files needed by Eclipse to setup the project.
To import the Scala IDE in your workspace simply click on ``File > Import``. The Eclipse Import dialog
will open. There, select ``General > Existing Projects into Workspace`` and click Next. A new dialog
will open. Browse to the folder that points to your cloned Scala IDE project's and select it.

A list of projects should then be loaded in the below white area. The only projects that you absolutely
need to import in Eclipse are ``org.scala-ide.sbt.full.library``, ``org.scala-ide.sdt.core`` and
``org.scala-ide.sdt.core.tests``. Select only those and click Finish.

There is **one more thing** to check to get ready to hack the Scala IDE project. You may
notice that the ``org.scala-ide.sbt.full.library`` project has build path errors. This project is
grouping together a bunch of SBT jars into one OSGI bundle. The project build path is configured by default to
reference the 2.10 version of the jars, so the configuration will be wrong if you are working with Scala 2.9.

To fix this, right click on the ``org.scala-ide.sbt.full.library`` project and then click "Properties". A
dialog will open. Click on "Java Build Path" and select the "Libraries" tab. You should see something
on the lines of:

.. image:: images/sbt-full-jars.png
   :width: 100%
   :target: ../../_images/sbt-full-jars.png

First remove all JARs, and then click the "Add JARs..." button and browse to ``org.scala-ide.sbt.full.library/lib``.
Select all JARs in the folder and click OK.

Now click the "Order and Export" tab and make sure to export all JARs click the "Select All" button.

.. image:: images/order-export.png
   :width: 100%
   :target: ../../_images/order-export.png


Click OK.

Additionally, if you're using scala 2.9, you need to right-click the 2.10 folder in the ``org.scala-ide.sdt.core`` project and select ``Build Path -> Remove from Build Path`` and add the 2.9 folder instead by right-clicking and selecting ``Build Path -> Use as Source Folder``.

If after rebuilding you see any errors, drop us a note in the `Scala IDE Developer
Mailing List <http://groups.google.com/group/scala-ide-dev?pli=1>`_.

Run the Scala IDE within Eclipse
--------------------------------

So, you managed to have the Scala IDE sources compiling, now it's time to learn how to run the Scala
IDE within Eclipse.

Doing this is especially useful if you need to do some manual debugging of the plug-in. Since the
Scala IDE uses weaving, you need to launch the IDE with weaving enabled, which is not configurable
in the vanilla launcher.

That is why we suggest you to install the
`Equinox Weaving Launcher <https://github.com/milessabin/equinox-weaving-launcher>`_.


.. _setup_install-equinox-weaving-launcher:

Install the Equinox Weaving Launcher
....................................

To install the Equinox Weaving Launcher, use the following Eclipse update site:

        http://www.chuusai.com/eclipse/equinox-weaving-launcher/

This adds two more launch configurations: Eclipse Application with Equinox Weaving, and JUnit plug-in
test with Equinox Weaving.

Create a run configuration for launching Eclipse with Scala IDE
...............................................................

To create a run configuration, right click on the ``org.scala-ide.sdt.core`` project and select
Run As > Equinox Weaving enabled Eclipse Application.  That should fire up a second Eclipse instance.
To quickly test that all is working fine, try to create a Scala project.

You are now ready to hack on the Scala IDE. Start by looking around, do some change and observe what
happens when you launch the second Eclipse's instance.

Read the rest of the developer documentation to get more insights about the overall architecture.

Run the test suite inside Eclipse
---------------------------------

You can use the built-in JUnit runner to run or debug the tests inside Eclipse. As for the normal run,
you need to the *Equinox Weaving Launcher*. In the *Run Configuration* Dialog, create a new configuration
using the **JUnit Plug-In test with Equinox Weaving**. Make sure you selected the *org.scala-ide.sdt.core.tests*
project, and choose the test class you want to run (use ``scala.tools.eclipse.TestsSuite`` to run all tests).

In the **Main** tab make the following adjustments:

* Choose **Run an application: [No Application] - Headless Mode**.

The window should look like this:

.. image:: images/setup.png

In the **Arguments** tab, make sure to add the following arguments to the VM arguments list:

.. code-block:: bash

   -Dsdtcore.headless -Dsdtcore.notimeouts

The first one tells the IDE to not try to open any windows or dialogs, while the second one disables
timeouts for certain actions (otherwise, on a slow or overloaded system, the tests might fail simply 
because of a timeout).


Additional information
----------------------

``org.scala-ide.sdt.aspects`` project
.....................................

The Scala IDE uses AspectJ to weave into Eclipse and hook in to JDT internals. If you want to work
on JDT integration within the Scala IDE, then it is a good idea to import
``org.scala-ide.sdt.aspects`` project in your Eclipse workspace and also install the `AspectJ
Eclipse plug-in <http://www.eclipse.org/ajdt>`_.


Using JRebel
............

If you want to use JRebel, there are two things you need to know:

1. You can simply add ``-noverify -javaagent:path/to/javarebel.jar`` to the VM arguments in the
   run configuration. For Windows, this is
   ``-noverify "-javaagent:C:\Program Files (x86)\ZeroTurnaround\JRebel\jrebel.jar"``.
2. JRebel is free for Scala users, see Free JavaRebel for Scala users, ZeroTurnaround announces.
