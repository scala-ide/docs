Building the Scala IDE
======================

The Scala IDE for Eclipse can be built on the command line using Maven, as is 
done, for example, during the Hudson-driven nightly builds on Amazon EC2. 


Requirements
------------

* You have cloned the Scala IDE project. Read the :doc:`Setup instruction <../setup/setup>` for 
  more information about this point.
* A recent JDK (the Oracle JDK 6 is recommended)
* Maven 3


.. _building_run-the-build:

Run the build
-------------

Using a Unix-like OS, the process is as follows. First, from the project's root, navigate to the 
build directory:

.. code-block:: bash

	$ cd org.scala-ide.build

Build using the provided scripts,

.. code-block:: bash

      # For builds relative to Scala 2.8.x
      $ ./build-ide-2.8.x.sh
      #
      # For builds relative to Scala 2.9.x
      $ ./build-ide-2.9.x.sh
      #
      # For builds relative to Scala trunk
      $ ./build-ide-trunk.sh

..note:: 
	When working on the Scala IDE you need to make sure that new code can be compiled with Scala 
	2.8.x, 2.9.x and 2.10 (trunk).

Assuming your build is successful you should find an Eclipse update site has been built in 
``org.scala-ide.sdt.update-site/target/site`` and a zipped version of the same at 
``org.scala-ide.sdt.update-site/target/site_assembly.zip``. You can install directly into Eclipse 
from this update site by adding it as a local update site via the Eclipse 
"Install New Software ..." action. Alternatively, if you make the unpacked site available via a web 
server, then the http URL of its root directory is acceptable as an ordinary Eclipse update site URL.



Build the Scala IDE with a local version of the Scala Compiler
--------------------------------------------------------------

.. note::

	Chances are that most of you will not need to be concerned by this. You need to build a local 
	version of the Scala compiler only if you intend to modify the Scala compiler and check how the 
	Scala IDE plugin reacts to the changes. If that is exactly what you want to do, keep reading.
	Otherwise, you can safely skip this section.

First, build the scala compiler, package into maven format and deploy locally,

.. code-block:: bash

    # You are in the main scala directory
    $ ant distpack-opt
    $ (cd dists/maven/latest; ant -Dlocal.snapshot.repository=${LOCAL_REPO} -Dlocal.release.repository=${LOCAL_REPO} deploy.snapshot.local)
    
    # If you use the standard .m2 location for maven, then the last command reduces to
    $ (cd dists/maven/latest; ant deploy.snapshot.local)

Go to directory where you cloned your scala-ide and run the necessary scripts that use your local 
trunk version

.. code-block:: bash

	# Packages the Scala compiler and library into OSGI bundles
    $ org.scala-ide.build-toolchain/build-toolchain-local-trunk.sh
    
    # Uses the above bundled compiler and library to build the Scala IDE
    $ org.scala-ide.build/build-ide-local-trunk.sh


If the build was successful you will end up with a right update-site - it contains the local changes 
you made in the scala compiler. 


.. note::

	If you get weird errors about missing methods, then you are probably mixing scala versions in the 
	scripts and the pluign. Before starting the new instance of Eclipse (with your version of the 
	Scala plugin) make sure that no errors occurred.
