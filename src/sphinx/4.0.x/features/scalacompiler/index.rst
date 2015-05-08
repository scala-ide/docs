.. include:: /global_defs.hrst

Scala Scopes Compilation |new| (since 4.1)
==========================================

Scala IDE allows for two different ways of project compilation. In first one all sources in project are seen by compiler regardless where their are stored and what are their output folders. 

Setting scopes compilation
--------------------------

There are multiple ways to start it:

* Opening the view. The view can be opened with ``Window → Show View → Scala Interpreter`` from the Scala perspective, or ``Window → Show View → Other... → Scala → Scala Interpreter`` from anywhere. This will display the list of available projects. The interpreter is initialized and configured to use the selected one.

* Executing some code. The |runSelection| ``Run Selection in Scala Interpreter`` command triggers the creation of a view for the current project if needed. The code currently selected in the editor is then run in the interpreter.

Settings change in existing project
-----------------------------------

1. Scala code can be entered directly in the interpreter view, in the ``Evaluate`` field by pressing ``Ctrl+Enter``.

   The previously entered commands are kept in a history. They can be browsed using the ``Ctrl+Up`` and ``Ctrl+Down`` key combinations.

   .. image:: images/feature-interpreter-01.png
      :width: 100%
      :target: ../_images/feature-interpreter-01.png

#. Existing code can be selected in the Scala Editor, and run in the interpreter using the |runSelection| ``Run Selection in Scala Interpreter`` command.

   The command can also be invoked using the ``Ctrl+Shift+x`` or ``Cmd+Shift+x`` key shortcut.

   .. image:: images/feature-interpreter-02.png
      :width: 100%
      :target: ../_images/feature-interpreter-02.png

#. An other way is to use the interpreter is combination with an editor to test new code.

   With a little bit of wrapping code, it is possible to create a setup where any code can be entered, and type checked. Then the code can be run in the interpreter to see its result.

   .. image:: images/feature-interpreter-03.png
      :width: 100%
      :target: ../_images/feature-interpreter-03.png

Toolbar
-------

The toolbar contains the following five buttons:

================= ================================= ================================================================================================
|replayAll|       Replay All Commands                Execute again all commands from the current session.
|terminate|       Terminate                         Stop the interpreter.
|terminateReplay| Terminate and Replay              Reset the interpreter, and execute again all commands from the current session.
|clear|           Clear Output                      Clear the view, but keeps the current session state.
|autoreplay|      Replay History on Project Rebuild If enable, the commands from the current session are executed every time the project is rebuilt.
================= ================================= ================================================================================================

.. |replayAll| image:: images/restart_co.gif
.. |terminate| image:: images/terminate_co.gif
.. |terminateReplay| image:: images/term_restart.gif
.. |clear| image:: images/clear_co.gif
.. |autoreplay| image:: images/refresh_interpreter.gif
.. |runSelection| image:: images/run_interpreter.gif

