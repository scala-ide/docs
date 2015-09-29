.. include:: /global_defs.hrst

Asynchronous Debugger |new|
=====================

The Asynchronous Debugger extensions facilitate debugging of applications that use actors, futures or even iteratees. There are two main features:

- Asynchronous Stack Recovery. Capture the call stack at the point where a future is created, or a message is sent to an actor.
- Step with Message. Follow a message send and stop in the receiving actor.

A few minor improvements, too:

- logical structures for actors (see at a glance the supervisor, current sender, supervising strategy)
- break on dead letters (breakpoint when a meessage arrives in dead letters)

For more details and demo, have a look at this `ScalaDays 2014 presentation`_

Configuration
-------------

The asynchronous extensions incurr a cost on your debugged application so they need to be explicitly enabled in your Debug Configuration. Look for the additional ``Async Debugger`` tab and enable the extensions.

You can also define new *program points* where stacks are captured. A program point is defined by the fully qualified (and desugared) method name to intercept, and a 0-based index (0 being ``this``) in the method parameters. The index defines the object to which this additional stack is attached. Whenever the program is stopped at a regular breakpoint, clicking on a variable that points to that object will bring in the latest captured stack in the ``Async Stack View``.

Async Stack
-----------

Step with Message
-----------------

Break on Dead Letters
---------------------


.. _ScalaDays 2014 presentation: https://www.parleys.com/tutorial/rethinking-debugger

