Building the Scala IDE
======================

The Scala IDE for Eclipse can be built on the command line using Maven, as is 
done, for example, during the `Hudson-driven nightly builds on Amazon EC2 <https://jenkins.scala-ide.org:8496/jenkins/>`_. 


Requirements
------------

* `JDK 5 <http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase5-419410.html>`_
  or `JDK 6 <http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase6-419409.html>`_
  (JDK 7 is **not** supported. As a matter of fact, there have been issues reported when running Eclipse with a Java 7 JVM. For instance,
  see https://bugs.eclipse.org/bugs/show_bug.cgi?id=364735).

* `Maven 3 <http://maven.apache.org/download.html>`_.

* You have cloned the Scala IDE project. Read :ref:`setup_fork-the-project` for 
  more information about this point.

You will also need a terminal to run some script. If you are on Windows, we
suggest you to install Cygwin (how could you live without it on Windows
anyway!?). It is not mandatory to use Cygwin, but in this documentation we
assume you have a way to run bash scripts, or else, you have the time and
motivation to look inside the existing scripts and convert them in Windows
batch files, if needed (if you do so, make sure to send a pull request our
way, and we will make the Windows scripts available to all contributors).


.. _building_run-the-build:

Run the build
-------------

Open your terminal, navigate to the project's root, and then:

.. code-block:: bash

	$ cd org.scala-ide.build

Build using the provided scripts,

.. code-block:: bash

      # For builds relative to Scala 2.9.x
      $ ./build-ide-2.9.x.sh
      #
      # For builds relative to Scala trunk
      $ ./build-ide-trunk.sh

.. note:: 

	When working on the Scala IDE you need to make sure that new code can be compiled with Scala 
	2.9.x and 2.10 (trunk).

Assuming your build is successful you should find an Eclipse update site has been built in 
``org.scala-ide.sdt.update-site/target/site`` and a zipped version of the same at 
``org.scala-ide.sdt.update-site/target/site_assembly.zip``. You can install directly into Eclipse 
from this update site by adding it as a local update site via the Eclipse 
"Install New Software ..." action. Alternatively, if you make the unpacked site available via a web 
server, then the HTTP URL of its root directory is acceptable as an ordinary Eclipse update site URL.



Build the Scala IDE with a local version of the Scala Compiler
--------------------------------------------------------------

.. note::

	Chances are that most of you will not need to read this section. You need to build a local 
	version of the Scala compiler only if you intend to modify the Scala compiler and check how the 
	Scala IDE plug-in reacts to the changes. If that is exactly what you want to do, keep reading.
	Otherwise, you can safely skip this section.

First, build the Scala compiler, package into maven format and deploy locally,

.. code-block:: bash

    # You are in the main Scala directory
    $ ant distpack-opt
    $ (cd dists/maven/latest; ant -Dlocal.snapshot.repository=${LOCAL_REPO} -Dlocal.release.repository=${LOCAL_REPO} deploy.snapshot.local)
    
    # If you use the standard .m2 location for maven, then the last command reduces to
    $ (cd dists/maven/latest; ant deploy.snapshot.local)

Go to directory where you cloned your Scala IDE and run the necessary scripts that use your local 
trunk version

.. code-block:: bash

    # Packages the Scala compiler and library into OSGI bundles
    $ org.scala-ide.build-toolchain/build-toolchain-local-trunk.sh
    
    # Uses the above bundled compiler and library to build the Scala IDE
    $ org.scala-ide.build/build-ide-local-trunk.sh


If the build was successful you will end up with a right update-site - it contains the local changes 
you made in the Scala compiler. 


.. note::

	If you get weird errors about missing methods, then you are probably mixing Scala versions in the 
	scripts and the plug-in. Before starting the new instance of Eclipse (with your version of the 
	Scala plug-in) make sure that no errors occurred.
