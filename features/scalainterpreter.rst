Scala Interpreter
=================

Scala IDE integrate the Scala REPL. It is set in a view called ``Scala Interpreter``. One REPL can be started by project. All artefacts defined or referenced in the projects are available inside the interpreter.

Starting the interpreter
------------------------

There are multiple ways to start it:

* directly, using the ``Window → Show View → Scala Interpreter`` from the Scala perspective, or ``Window → Show View → Other... → Scala → Scala Interpreter`` from anywhere. The view will ask which project it needs to be linked too.

* by selecting some Scala code in an editor, and using the ``Run Selection in Scala Interpreter`` command. The command can be invoked using the |runSelection| button in the toolbar, or the ``Ctrl+Shitf+x`` or ``Cmd+Shift+x`` key shortcut. The view is automatically configured to use the current project.

.. |runSelection| image:: /images/run_interpreter.gif

Using the interpreter
---------------------

Scala code can be entered directly in the interpreter view.

.. image:: /images/feature-interpreter-01.png

Existing code can be selected and run in the interpreter using the ``Run Selection in Scala Interpreter`` command.

.. image:: /images/feature-interpreter-02.png

Toolbar
-------

The toolbar contains the following five buttons:

================= ================================= ================================================================================================
|replayAll|       Replay All Comands                Execute again all commands from the current session.
|terminate|       Terminate                         Stop the interpreter.
|terminateReplay| Terminate and Replay              Reset the interpreter, and execute again all commands from the current session.
|clear|           Clear Output                      Clear the view, but keeps the current session state.
|autoreplay|      Replay History on Project Rebuild If enable, the commands from the current session are executed everytime the project is rebuilt.
================= ================================= ================================================================================================

.. |replayAll| image:: /images/restart_co.gif
.. |terminate| image:: /images/terminate_co.gif
.. |terminateReplay| image:: /images/term_restart.gif
.. |clear| image:: /images/clear_co.gif
.. |autoreplay| image:: /images/refresh_interpreter.gif

