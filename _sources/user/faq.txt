Frequently Asked Questions
==========================

General
-------

I have an existing Java project and I wish to add Scala files. How do I convince Eclipse to work with Scala?
............................................................................................................

Right click on the project in the Package Explorer view, and in the context menu select ``Configure → Add Scala Nature``.

I am running out of stack space in Eclipse.  How do I increase the stack size?
..............................................................................

If you are starting Eclipse from the command line, you can supply virtual machine arguments, including stack size, like this:

.. code-block:: bash

   eclipse [normal arguments] -vmargs -Xss8M [more VM args]

You can also edit eclipse.ini in the Eclipse installation/application. There, lines that look like

.. code-block:: bash

   -vmargs
   -Xms40m
   -Xmx256m

can be replaced with something like (for an 8 megabyte stack, 700MB initial heap, 2GB maximum heap)

.. code-block:: bash

   -vmargs
   -Xss8m
   -Xms700m
   -Xmx2048m

Scala IDE complains about 'no Scala library...' or 'More than one Scala library...'
...................................................................................

In Scala IDE 2.0.0, a classpath validator has been added to check that the Scala library has been correctly setup. It is checking two points: that the project build path contains only one Scala libraries, and that its version is compatible with the installed version of Scala IDE.

Not using m2eclipse
___________________

It needs to be fixed manually.

* If the error is 'no Scala library...', one Scala library needs to be added to the build path. The simplest thing is to add the Scala library container: right-click on the project in the Package Explorer view, then in the context menu select ``Build Path → Add Libraries...``, and add the ``Scala Library``.


* If the error is 'more than one Scala library...', the number of Scala libraries needs to limited to one in the build path. If possible, make it being the Scala library container provided by Scala IDE.

Using m2eclipse
_______________

With projects imported using m2eclipse, the classpath validator can report an error because the Scala library is visible more than once in the build path. The `m2eclipse-scala`_ project was created to, among other things, fix these classpath problems.

Use this `update site`__ to get the latest version.

After installation, re-importing your projects should get them configured correctly.

__ http://alchim31.free.fr/m2e-scala/update-site/

Scala Interpreter
-----------------

What are the limitations?
.........................

* The colon commands available in the terminal REPL are not supported.
* Commands cannot be forcibly killed. For example, if an infinite loop is launched, it will continue in the background until Eclipse is shutdown.

.. _faq_known-issues:

Know Issues
-----------

Scala errors on all Unicode arrows
..................................

**Symptoms**:

The code contains Unicode niceties like ``←`` and ``⇒``, but the editor doesn't seem to be able to display them, and errors are reported at their location.

**Solution**:

The operating system is not using UTF-8 by default, and its default encoding is used inside Eclipse.

The encoding used to open files can be configured at different levels. Most of the time, setting Eclipse default encoding to UTF-8 in ``General → Workspace`` is enough. But in some case, the wrong encoding might also have been set in the project properties, or even the file properties.

Red screen of death (red squiggles everywhere)
...............................................

The number one cause of nothing works is a mismatch between the Scala version of the Eclipse plug-in and your project's. Make sure there is only one version of the Scala library on your classpath, and that it matches the version provided by Scala IDE.
The prime suspect is Maven Dependencies, which can download and add an incompatible scala-library.jar.

The classpath validator added in Scala IDE 2.0.0 should detect this problem and provide meaning full problem markers.

No completions available
........................

**Symptoms**:

When pressing Ctrl-Space, the list doesn't contain code completion proposals, only templates, but other semantic actions (such as hyperlinking) work fine.

**Solution**:

During the development of version 2.0.0, the configuration of the Scala completion engines has been modified to make sure that no more disabled by mistake. If Scala IDE has been updated from an old beta version, it is possible that the Scala completion engines (``Scala Completions`` and ``Scala Completion (Java sources)``) need to be re-enabled.

The diagnostic tool at ``Scala → Run Setup Diagnostic`` allows to fix this problem. Make sure that ``Use Scala-compatible JDT content assist proposals`` is enabled.

.. image:: images/setup-diagnostics-01.png

Eclipse freezes (deadlock)
..........................

**Symptoms**:

The IDE is completely frozen.

**Workaround**:

If the deadlock is an instance of `#1000317`_ (deadlock in the JDT weaving code) you can edit your eclipse.ini file using the recommended settings:

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

If the thread dump doesn't match the one in `#1000317`_, please open a new ticket with your thread dump attached.

NoClassDefFoundError (scala/tools/nsc/settings/MutableSettings$SettingValue)
............................................................................

**Symptoms**:

After upgrading the Scala plug-in, ``java.lang.NoClassDefFoundError: scala/tools/nsc/settings/MutableSettings$SettingValue`` exception is reported for all Scala projects in the workspace. Scala IDE is then unusable.

**Solution**:

If you have several update sites providing different version of Scala IDE, Eclipse may have decided that a newest Scala library should be used instead of the one provided by the plug-in to be installed. To avoid this problem, make sure to uncheck the ``Contact all update sites during install to find required software`` option. It is situated at the bottom of the ``Help → Install New Software...`` dialog.

.. _#1000317: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000317
.. _m2eclipse-scala: https://github.com/sonatype/m2eclipse-scala
