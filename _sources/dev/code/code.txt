Codebase
========

In this page you will learn about some specific aspect of the Scala IDE's codebase, such as 
available components and some of the pattern in use (or that we'd like to foster usage).

Logging
-------

Logging is an important aspect of any application, and the Scala IDE is no exception. Logging the 
right information and provide the user with a way to report logs can go a long way in understanding 
the reason of a bug, and it can definitely save you some debug time!

Now, you may know that the Eclipse environment already provides a logging component. As a matter of 
fact, a plugin can get a logger instance by simply calling ``getLog()`` on the ``plugin``'s reference. The 
returned logger, of type `ILog <http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fcore%2Fruntime%2FILog.html>`_, 
can be used to communicate to the user any useful information. All log messages sent through 
the Eclipse logger are collected in `Eclipse Error Log view component <http://help.eclipse.org/indigo/index.jsp?topic=/org.eclipse.pde.doc.user/guide/tools/views/error_log.htm>`_, 
hence they can be easily consulted by users.

Though, we believe that the Eclipse logger is not flexible enough to accomodate all usage. One reason is 
that you don't always want the user to see a log message. For instance, debug messages are often of no use 
for the user, but they can provide very helpful information when tracking down the source of an 
issue.

Inside the Scala IDE we offer two different loggers. The first one, is a handle on the 
Eclipse logger described above. The second one, is a logger backed by Log4j (which we will refer to as 
the "default logger" from now on). Log messages sent to the default logger are stored in a file named 
``scala-ide.log``, located in the ``${workspace}\.metadata\.plugins\org.scala-ide.sdt.core\`` directory. 
Also, note that users can easily control the amount of logged information through Logging preference page 
(accessible under the Eclipse Preferences > Scala > Logging).

To get a handle on the loggers, you only need to mix-in the ``HasLogger`` trait defined in package 
``scala.tools.eclipse.logging``. The implementation should be somewhat similar to the following:

.. code-block:: scala

	trait HasLogger {

	  protected[this] lazy val logger: Logger = {
	    val clazz = this.getClass
	    LogManager.getLogger(clazz)
	  }
	  
	  protected[this] def eclipseLog: Logger = EclipseLogger
	}
	
Deciding which logger you should use is simple. If you want to inform the user, use the 
``eclipseLog`` instance. For instance, if a severe error occurred (i.e., an unexpected exception), it 
is a good idea to inform the user!
In all other cases (or if you are unsure), prefer the default ``logger``. Also, note that 
messages sent through the ``eclipseLog`` are also forwarded to the default ``logger`` (meaning that 
you never need to log the same message twice). Further, all printings in the Standard Output or the 
Standard Error are redirected to the default ``logger``.

In short, the ``scala-ide.log`` file always contain the complete list of log messages occurred within the Scala IDE!

