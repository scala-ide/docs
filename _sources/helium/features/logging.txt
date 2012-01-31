Logging
=======

Logging isn't likely to be the most wanted feature, and we actually hope you won't even need to know about it. 
Though, software can crash, and decent logging can go a long way into understanding the reasons behind a failure.
Thus, we have improved the logging infrastructure so that it can be easily controlled by you. In this page we 
discuss how you can manage logging in the Scala IDE.


Settings
--------

You can access the logging settings in the Eclipse Preferences, click on the ``Scala`` item and select ``Logging``.
You should see the following page:

.. image:: ../images/logging-preferences.jpg

By default, the log level is set to ``WARN``. That should be enough for the general uses and it should still provide 
some useful information in the event of an error. Naturally, you are free to change the log level at any time (though, 
remember that increasing logging may have an observable performance penalty).

All logging information are stored in a ``scala-ide.log`` file located in the ``${workspace}\.metadata\.plugin\org.scala-ide.sdt.core\`` 
directory, where ``${workspace}`` is the directory provided when starting up Eclipse, i.e., your Eclipse workspace. This file can grow up 
to 10MB and it automatically rolls over once the limit is reached. At any time, you can easily check the log's content by clicking on the link located 
at the bottom of the page.

For convenience, you can also decide to output all log messages directly in the terminal, but mind that you need to start Eclipse from the 
command line to be able to see the log's output. For instance, I can start Eclipse from the command line with the following command (on Mac OSX):

.. code-block:: bash

	$ /Applications/eclipse/Eclipse.app/Contents/MacOS/eclipse

