Scala Debugger
==============

Goals
-----

The idea is to provide a debugger inside Scala IDE more targeted to Scala code.

The main goals are:

* Hide the artifacts created by the Scala compiler
* Have an intuitive stepping through Scala code
* Identify elements as Scala

Status
------

* Smart stepping over anonymous functions (for comprehension, collection methods, ...).
* Terminate launch action
* The structure of the Scala implementation of the Eclipse debug model.
* No good labels, icons, names, values for the model elements.
* No caching of any data -> likely creating the ObjectCollectedExceptions
* No stepping in/out
* No drop to frame
* No source locator support for files not in the source folders.

Development setup
-----------------

This feature is developped in the branch `feature/scala-debugger-1000864`__ on scala IDE's github.

__ https://github.com/scala-ide/scala-ide/tree/feature/scala-debugger-1000864

The setup is the same as for master. It contains 2 new plugins: org.scala-ide.sdt.debug and org.scala-ide.sdt.debug.tests.

In Eclipse
..........

The Scala debugger makes use of the `JDI`_ to communicate with the VM. For some valid reasons, the jdt.debug plug-in provides a non-generic version of it. The Scala debugger uses the newer generic version, so some tweaking is needed inside Eclipse.

On Mac OS X
^^^^^^^^^^^

The generic version of the JDI is included by default in the VM libraries, just remove the ``JAVA_HOME/lib/tools.jar`` entry from the Java build path to have the project to compile.

On the other OSes
^^^^^^^^^^^^^^^^^

On non-Mac OS X systems, JDI is not part of the VM default libraries. To steps are needed to have the project to compile correctly:

* create a ``Classpath Variable`` in ``Window → Preferences → Java → Build Path → Classpath Variables`` named ``JAVA_HOME``, which points to the root folder of your JDK installation.
* in ``Window → Preferences → Java → Installed JREs``, edit your JDK, and use ``Add External JARs`` to add the ``JDK_HOME/lib/tools.jar`` library.


.. _JDI: http://docs.oracle.com/javase/6/docs/jdk/api/jpda/jdi/index.html


