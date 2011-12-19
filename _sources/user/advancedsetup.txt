Advanced Setup
==============

This page contains information to help you configure Eclipse and Scala IDE, to increase performances and ease-of-use.

Eclipse Configuration
---------------------

The default Eclipse heap configuration is often inadequate for the Scala IDE. It is strongly recommended to edit the ``eclipse/eclipse.ini`` (``eclipse/Eclipse.app/Contents/MacOS/eclipse.ini`` on Mac osX) file and add the following lines to increase various heap limits and virtual machine options:

.. code-block:: bash

   --launcher.XXMaxPermSize
   256m
   -vmargs
   -Xms256m
   -Xmx1024m
   -XX:PermSize=64m
   -Xss1M
   -server
   -XX:+DoEscapeAnalysis

One recent Oracle JVM, the following might also be beneficial:

.. code-block:: bash

   -XX:+UseConcMarkSweepGC

As is the following on Oracle 64-bits JVM:

.. code-block:: bash

   -XX:+UseCompressedOops

Required Preferences
--------------------

* **JDT Weaving**

  Scala IDE uses AOP to tweak the Eclipse JDT, so JDT weaving has to be enable. Otherwise, Scala files would be managed as Java files.

  It is set in the ``JDT Weaving`` section of the Eclipse preferences [#preferences]_. It should say *'JDT Weaving is currently ENABLED'*. If it is not the case, use the ``Click to Enable`` button to enable it.

  .. image:: images/advancedsetup-jdtweaving-01.png

Optional Preferences
--------------------

* **Heap Status**

  When the memory usage nears the limit, the Garbage Collector activity increases (when the compiler runs, ...) and system slows down. It is good to keep a eye on the heap status. It is possible by enabling ``Show heap status`` in the ``General`` section.

  .. image:: images/advancedsetup-heapstatus-01.png

Performance
-----------

Garbage Collection activity and swap can be really bad for performance. It can trigger Eclipse freezes and general system slow down. 

Monitoring what is happening is the best option to keep a healthy system:

* to monitor CPU and swap, use your native system monitor (some of the other system monitors are big resource users themselves).
* to monitor Garbage Collector activity, use *visualvm* on the eclipse instance. It is a profiling tool provided with jdk 1.6.x. It is possible also to get a feel of the memory usage with `the heap status bar <Optional Preferences>`_ in Eclipse.

Depending of the problem, the memory allocated to the JVM can be increased, or some of the other application closed (like the web browser, or some flash application, ...).

Additional Links
----------------

* `Optimizing Eclipse performances`__ from `Normation`__.

* `What are the best JVM settings for Eclipse?`__ from `Stack Overflow`__ forum

* `My Eclipse Setup`__ from davidB

__ http://blog.normation.com/2010/05/24/optimizing-eclipse-performances/
__ http://blog.normation.com/
__ http://stackoverflow.com/questions/142357/what-are-the-best-jvm-settings-for-eclipse
__ http://blog.normation.com/
__ http://dwayneb.free.fr/posts/my_eclipse_setup/

.. rubric:: Footnotes

.. [#preferences] The Eclipse preferences are accessible using ``Windows → Preferences`` (or ``Eclipse → Preferences`` on Mac osX).
