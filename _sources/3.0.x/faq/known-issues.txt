Know Issues
-----------

Do Not Enter road sign when opening a Scala Editor
..................................................

**Symptoms**:

When opening a Scala editor, the error "Could not open the editor: org.eclipse.jdt.internal.core.CompilationUnit cannot be cast to scala.tools.eclipse.InteractiveCompilationUnit" (or something similar) appears.

**Solution**:

The Scala IDE uses weaving to behave as (much) more than a Java editor on Scala files, and this is the message you get when it is not active.

JDT weaving is activated by default. Moreover, Scala plugin
checks it is activated at every launch. The easiest way to debug
this is therefore to accept activation of JDT weaving at
launch. Otherwise, you can look into turning it on manually using
the :doc:`Scala Diagnostics Dialog
</current-user-doc/advancedsetup/index>`.

Scala errors on all Unicode arrows
..................................

**Symptoms**:

The code contains Unicode niceties like ``←`` and ``⇒``, but the editor doesn't seem to be able to display them, and errors are reported at their location.

**Solution**:

The operating system is not using UTF-8 by default, and its default encoding is used inside Eclipse.

The encoding used to open files can be configured at different levels. Most of the time, setting Eclipse default encoding to UTF-8 in ``General → Workspace`` in the preferences [#preferences]_ is enough. But in some case, the wrong encoding might also have been set in the project properties, or even the file properties.

Red screen of death (red squiggles everywhere)
...............................................

The number one cause of *nothing works* is a mismatch between the Scala version of the Eclipse plug-in and your project's. Make sure there is only one version of the Scala library on your classpath, and that it matches the version provided by Scala IDE.
The prime suspect is Maven Dependencies, which can download and add an incompatible scala-library.jar.

The classpath validator added in Scala IDE 2.0.0 should detect this problem and provide meaningful problem markers.

No completions available
........................

**Symptoms**:

When pressing Ctrl-Space, the list doesn't contain code completion proposals, only templates, but other semantic actions (such as hyperlinking) work fine.

**Solution**:

During the development of version 2.0.0, the configuration of the Scala completion engines has been modified to make sure that no more disabled by mistake. If Scala IDE has been updated from an old beta version, it is possible that the Scala completion engines (``Scala Completions`` and ``Scala Completion (Java sources)``) need to be re-enabled.

The diagnostic tool at ``Scala → Run Setup Diagnostic`` allows to fix this problem. Make sure that ``Use Scala-compatible JDT content assist proposals`` is enabled.

.. image:: images/setup-diagnostics-01.png

Bad completion when using companion object in Java
..................................................

Using the Java code assist to access a Scala companion object generates invalid code.

.. code-block:: scala

   package stest

   class S3

   object S3 {
     def some { }
   }

.. code-block:: java

   package jtest;

   import stest.S3;

   public class J {
     public void s() {
       S3$.// call code assist here, select MODULE$
     }
   }

This a JDT problem. See `#1729`__.

__ http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1729

Eclipse freezes (deadlock)
..........................

**Symptoms**:

The IDE is completely frozen. 

A known manifestation of this problem occurs when the ``SmartIndenter`` kicks in (for 
instance when a return carriage is inserted in the Eclipse Editor), which is particularly 
annoying since all unsaved work will be lost.

**Solution**:

Apparently, this problem is due to a bug in the Oracle JVM, and it is fixed in the JRE 7 (`read 
more... <https://bugs.eclipse.org/bugs/show_bug.cgi?id=377609>`_). Hence, you should not experience 
this problem if you upgrade to JRE 7 or newer.

If you cannot use the JRE 7, then you can try the workaround described below.

**Workaround**:

If the deadlock is an instance of `#1000317`_ (deadlock in the JDT weaving code) or `#1000996`_ (deadlock during indentation) you can edit your eclipse.ini file using the recommended settings:

.. code-block:: bash

   -XX:+UnlockDiagnosticVMOptions
   -XX:+UnsyncloadClass
   -Dosgi.classloader.lock=classname

This will reduce the probability of getting a deadlock, but wont fix it.

If you encounter this problem, the best thing is to take a thread dump:

   Once the IDE is frozen, the following command provide the process id of the running virtual machines:

   .. code-block:: bash

      $ jps -v

   Then this command generates the thread dump:

   .. code-block:: bash

      $ jstack <pid>

If the thread dump doesn't match the one in `#1000317`_ or `#1000996`_, please open a new ticket with your thread dump attached.



NoClassDefFoundError (scala/tools/nsc/settings/MutableSettings$SettingValue)
............................................................................

**Symptoms**:

After upgrading the Scala plug-in, ``java.lang.NoClassDefFoundError: scala/tools/nsc/settings/MutableSettings$SettingValue`` exception is reported for all Scala projects in the workspace. Scala IDE is then unusable.

**Solution**:

If you have several update sites providing different version of Scala IDE, Eclipse may have decided that a newest Scala library should be used instead of the one provided by the plug-in to be installed. To avoid this problem, make sure to uncheck the ``Contact all update sites during install to find required software`` option. It is situated at the bottom of the ``Help → Install New Software...`` dialog.

.. _#1000317: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000317
.. _#1000996: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000996

.. _m2eclipse-scala: https://github.com/sonatype/m2eclipse-scala

.. [#preferences] The Eclipse preferences are accessible using ``Windows → Preferences`` (or ``Eclipse → Preferences`` on Mac osX).
