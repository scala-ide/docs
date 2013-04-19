Building the Scala IDE
======================

The Scala IDE for Eclipse can be built on the command line using Maven, as is
done, for example, during the `Jenkins-driven nightly builds <https://jenkins.scala-ide.org:8496/jenkins/>`_.


Requirements
------------

* `JDK 5 <http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase5-419410.html>`_
  or `JDK 6 <http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase6-419409.html>`_
  (JDK 7 is **not** supported. As a matter of fact, there have been issues reported when running Eclipse with a Java 7 JVM. For instance,
  see https://bugs.eclipse.org/bugs/show_bug.cgi?id=364735).

* `Maven 3 <http://maven.apache.org/download.html>`_.

* You have cloned the Scala IDE project. Read :ref:`setup_fork-the-project` for
  more information about this point.

.. _building_run-the-build:

Run the build
-------------

If you just want to build Scala IDE, a script triggers the different step needed to generate Scala IDE.


From the project root, run the following command to build Scala IDE for Scala 2.10:

.. code-block:: bash

   $ ./build-all.sh


or the following to build Scala IDE for Scala 2.11:

.. code-block:: bash

   $ ./build-all.sh -P scala-2.11.x clean install

.. note::

        When working on the Scala IDE you need to make sure that new code can be compiled with Scala
        2.10 and 2.11 (trunk).

Assuming your build is successful you should find an Eclipse update site has been built in
``org.scala-ide.sdt.update-site/target/site`` and a zipped version of the same at
``org.scala-ide.sdt.update-site/target/site_assembly.zip``. You can install directly into Eclipse
from this update site by adding it as a local update site via the Eclipse
"Install New Software ..." action. Alternatively, if you make the unpacked site available via a web
server, then the HTTP URL of its root directory is acceptable as an ordinary Eclipse update site URL.

The build in details
--------------------

The build relies only on maven and maven profile.

There are 4 parts:

* the *root* pom is the parent pom. It contains version numbers, default configurations and the profiles
* *org.scala-ide.build-toolchain* creates the osgi bundles for the Scala library, compiler and sbt
* *org.scala-ide.toolchain.update-site* creates an update site containing the bundles created by the build-toolchain (needed for the nightly builds)
* *org.scala-ide.sdt.build* compiles, tests and creates the update site for Scala IDE

The toolchain update site and the SDT build depend on the fact that the root pom and toolchain have been compiled and installed in the local maven repository using ``mvn install``.

After an initial complete build, maven can be used from any subproject.

``build-all.sh`` is simply a helper script that calls maven on the different parts. Running it is equivalent to:

.. code-block:: bash

   $ mvn -P scala-2.10.x clean install
   $ cd org.scala-ide.build-toolchain
   $ mvn -P scala-2.10.x clean install
   $ cd ../org.scala-ide.sdt.build
   $ mvn -P scala-2.10.x clean install

or for Scala 2.11.x:

.. code-block:: bash

   $ mvn -P scala-2.11.x clean install
   $ cd org.scala-ide.build-toolchain
   $ mvn -P scala-2.11.x clean install
   $ cd ../org.scala-ide.sdt.build
   $ mvn -P scala-2.11.x clean install

Running the memory leak test
----------------------------

Scala IDE has a memory leak test. This test compiles 50 times the Scala compiler as a Scala IDE project, checking the memory consumption between each run.

This test is not run by default in a normal build. org.scala-ide.sdt.core.test contains a special profile to run the test.

Use the following command to run it after having build the toolchain.

.. code-block:: bash

   # from org.scala-ide.sdt.build
   $ mvn --projects ../org.scala-ide.sdt.aspects,../org.scala-ide.sdt.core,../org.scala-ide.sdt.core.tests -P scala-2.10.x,memory-test clean integration-test

The ``--projects`` option tells maven which modules to build, as we don't need to build all of them in this case. The ``memory-test`` profile contains a sightly modified configuration for ``sdt.core.test``. It pulls and extracts the Scala compiler source needed for the test, and sets the MemoryLeaksTest test to be run.

Build the Scala IDE with a local version of the Scala Compiler
--------------------------------------------------------------

.. note::

        Chances are that most of you will not need to read this section. You need to build a local
        version of the Scala compiler only if you intend to modify the Scala compiler and check how the
        Scala IDE plug-in reacts to the changes. If that is exactly what you want to do, keep reading.
        Otherwise, you can safely skip this section.

Build the Scala compiler, package into maven format and deploy locally,

.. code-block:: bash

    # From the main Scala directory
    $ ant distpack-opt
    $ (cd dists/maven/latest; ant deploy.snapshot.local)

Then rebuild Scala IDE, the build will automatically pickup the compiler which was installed locally.

.. code-block:: bash

    # From the main Scala IDE directory
    $ ./build-all.sh

When the build is successful, a complete update-site, with the local changes
you made in the Scala compiler, is available in ``org.scala-ide.sdt.update-site/target/site``.

.. note::

        If you get weird errors about missing methods, then you are probably mixing Scala versions in the
        scripts and the plug-in. Before starting the new instance of Eclipse (with your version of the
        Scala plug-in) make sure that no errors occurred.
