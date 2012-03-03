Setup and use the Scalac project with the Scala IDE
===================================================

What is in this guide?
----------------------

This guide will show you how to configure Eclipse and your workspace for development work on the `Scala compiler`_. 

Prerequisites
.............

*   `Eclipse`_ 3.7.2 (Indigo) with Scala IDE for Scala 2.10 installed (update site: http://download.scala-ide.org/nightly-update-master-trunk).

    Check the :ref:`getting started <gettingstarted_getting-started>` page for instructions on how to install Scala IDE.

*   A basic knowledge of the Eclipse user interface is required.

Setting up Eclipse
-------------------

In order to build the Scala compiler inside Eclipse, you need to make sure Eclipse runs with enough heap. We recommend at least 2GB, but be generous if you can. The detailed instructions on how to do that can be found in :ref:`advanced setup <advancedsetup_advanced-setup>`

.. note:: 

  If you have an older version of the Scala IDE plugin, you may run into trouble when updating to the latest nightly. Frequent changes in the compiler version numbering scheme may confuse Eclipse, and keep an older version of the compiler bundle that doesn't match the IDE. To make sure things run smoothly, you can uninstall the previous version. There is `command line tool`_ for doing that without firing up Eclipse.


Setting up the Eclipse project
-------------------------------

*  Clone the Scala repository

.. code-block:: bash

  git clone git://github.com/scala/scala.git

*  Rename the sample project and classpath file

.. code-block:: bash

  cd scala
  cp project.SAMPLE .project
  cp classpath.SAMPLE .classpath

*  Pull the binary dependencies::

.. code-block:: bash

  ./pull-binary-libs.sh 

*  Import the project in Eclipse (``File → Import Existing Projects into Workspace``)

The sample project file configures only the scala *compiler* sources as part of your project. The default output directory is ``build/quick/classes/compiler``.

Adding the Scala library *sources* to your project (optional)
---------------------------------------------------

If you need to develop the Scala library, you need to add the library source folder to the ``Java Build Path``. Since the default output directory is ``build/quick/classes/compiler``, you need to change that as well, so that library classes go instead to ``build/quick/classes/library``. 

*  Open Project Properties and go to the ``Java Build Path`` page

*  Make sure ``Allow output folders for source folders`` is checked

*  Add a new source folder for ``src/library``

*  Change the output folder for ``src/library`` to be ``build/quick/classes/library``

*  Also Change the output folder for ``src/compiler`` to be ``build/quick/classes/compiler``, instead of the default value

In the end, your project properties should look like this:

    .. image:: images/scalac-properties.png


Using Eclipse for compiler development
--------------------------------------

Now you are ready to start using Eclipse on the Scala compiler itself. Eclipse works by always keeping the binaries up to date, building each time you save a file. Since the compiler is such a large project, and has intricate dependencies, building may take a long time even for small changes. We suggest you switch off ``Project → Build Automatically`` and build explicitly (``CMD-B``).

The builder inside Eclipse is based on `Sbt`_, which is correct but conservative. The Scala IDE has a different builder that is much more aggressive in tracking dependencies, but which may sometimes be incorrect. You can enable it on the ``Build Manager`` page on Scala Compiler properties.

.. note::

  Mac OS Users: the default encoding for the JVM (and picked up by Eclipse) is ``MacRoman``. To change the encoding to ``UTF-8`` you have to go to ``Eclipse Preferences → General → Workspace`` and choose the correct ``Text File Encoding``.


Launching and Debugging scalac
------------------------------

Since the output directories are set to the default ``ant`` build directories, all the runner scripts work out of the box. If you built the project, you can run ``build/quick/bin/scalac`` on the command line, and it will pick your changes. However, the interesting part is being able to use the Eclipse debugger.

To launch scalac from Eclipse you need to create a ``Run Configuration``, from ``Run → Run Configurations``. Choose ``Scala Application`` and set the main class to ``scala.tools.nsc.Main``. On the ``Arguments`` page, you need to explicitly set the path to the Scala library (that is what the runner script does for you on the command line):

.. code-block:: bash

  -bootclasspath ${project_loc:/scala}/build/quick/classes/library -d /tmp /Users/dragos/sandbox/bugs/ticket3429.scala

The run configuration should look like this:

.. image:: images/scalac-launch.png

Now you can launch the scala compiler, by choosing either ``Run`` or ``Debug`` from the menu. You can set breakpoints and step through Scala code just as you'd expect.

Cheat sheet
-----------

Here's a collection of the shortcuts I use the most in my daily development. Hopefully they will be helpful for others:

*  ``CMD-3`` Quick Access -- a huge time saver. Opens up a dialog with incremental search on all commands available on the platform

*  ``CMD-Shift-R`` Open Resource -- opens up a dialog with incremental search on all files in the workspace

* ``CMD-.`` Go to the next error in the current editor

* ``F2`` Show the error for the position under the cursor

* ``F3`` Navigate to definition (same as ``CMD-click`` on an identifier)

* ``CMD-O`` Quick Outline -- opens up a dialog with incremental search on all definitions in the current editor

* ``CMD-J`` Incremental search

* ``CMD-F11`` Launch the debugger. By default it tries to launch the current file. I configure Eclipse to always launch the last application.

* ``CMD-Shift-F11`` Launch the application (with no debugger attached)

If you are new to Eclipse, you should have a look at the :ref:`getting started <gettingstarted_getting-started>` guide and watch the screencast.


Swapping the Scala compiler inside Eclipse (*risky*)
------------------------------------------

The Scala plugin packages a Scala compiler and library (since you are reading this, it's most probably the nightly version of master). There may be times when you'd like to change it for your own version: say, you are developing a new feature that has not yet been included in Scala master, or you fixed a bug and can't wait until the next nightly.

.. warning::

  This is a risky operation. Given the tight dependency between the IDE and the compiler, you must be sure you are swapping two binary-compatible versions of the compiler. This may not work if the plugin was built against a much older version of ``scalac`` than the one you are replacing it with.

If you're still here, go ahead and use the `update-scala.sh`_ script. Point it to your locally built scala, for instance ``build/pack``.

.. code-block:: bash

  $ update-scala.sh --eclipse-dir /Applications/eclipse update build/pack

If you run into trouble, the safest way out is to uninstall the plugin and re-install it. You can do that on the command line using the `update-director.sh`_ script.

.. code-block:: bash

  $ eclipse-director.sh --eclipse-dir /Applications/eclipse uninstall
  Unnstalling..
  Uninstalling org.scala-ide.sdt.feature.feature.group 2.1.0.nightly-2_10-201203020544-24a4734.
  !SESSION 2012-03-02 15:27:37.712 -----------------------------------------------


Feedback
--------

This guide is managed through in the `Scala IDE documentation project`_ on github.
Please use github tickets and pull requests system for feedback.

Iulian Dragos - `@jaguarul`_ 

based on the document created by Grzegorz Kossakowski - `@gkossakowski`_ and improved by `Vlad Ureche`_.


.. _#1000907: http://www.assembla.com/spaces/scala-ide/tickets/1000907
.. _Scala IDE: http://www.scala-ide.org
.. _Scala compiler: https://github.com/scala/scala
.. _Scala IDE documentation project: https://github.com/scala-ide/docs
.. _Eclipse: http://www.eclipse.org/
.. _embedded documentation: http://localhost:9000/@documentation/Home
.. _documentation website: http://docs.scala-lang.org/
.. _@jaguarul: https://twitter.com/jaguarul
.. _@gkossakowski: https://twitter.com/gkossakowski
.. _command line tool: http://scala-ide.org/blog/director-script.html
.. _Sbt: https://github.com/harrah/xsbt
.. _Vlad Ureche: http://people.epfl.ch/vlad.ureche
.. _update-scala.sh: https://github.com/scala-ide/scala-ide/blob/master/update-scala.sh
.. _update-director.sh: https://github.com/scala-ide/scala-ide/blob/master/update-director.sh