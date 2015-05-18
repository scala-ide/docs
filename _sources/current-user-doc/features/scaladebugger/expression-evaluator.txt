.. Copyright (c) 2015 Contributor. All rights reserved.

.. include:: /global_defs.hrst

.. _expression-evaluator-user-docs:

Expression evaluator user documentation
=======================================

Scala debugger uses new expression evaluator, which translates user code into invocations of ``JDI`` remote calls. This
allows evaluation of expression in context of some breakpoint, with access to local variables and methods.

Expression evaluator adds separate view to Scala IDE (similar to ``Scala interpreter`` view). It also enables conditional breakpoints - breakpoints that are enabled only if some condition is met.

Evaluator view
--------------

To open evaluator view use ``Window → Show View → Other → Debug → Scala Expression Evaluator``. In there, any
expression can be evaluated in context of your current breakpoint. This allows usage any values and types visible
from breakpoint and (possibly) modification of state of your application.

.. image:: images/expression-evaluator/expression.png
   :width: 100%
   :alt: Sample expression evaluation

``CTRL+ENTER`` evaluates expression. ``CTRL+Up`` and ``CTRL+Down`` browses expression history. Code completion can be
triggered with ``CTRL+Space`` (enabled in properties).

``Tree view`` part contains structure of result which can be traversed to inspect it further (see also
:ref:`logical-structures-support`).

.. note:: Evaluator view works only when using Scala debugger and on ``SUSPENDED`` threads.

It is also possible to immediately evaluate a selected text from the editor. One can do that using ``CTRL+SHIFT+K``
shortcut or the dedicated button. If there's no selected text, a whole line containing a cursor will be sent to the evaluator.

.. image:: images/expression-evaluator/run-selection-in-ee.png
   :alt: Run Selection in Scala Expression Evaluator

Configuring expression view
~~~~~~~~~~~~~~~~~~~~~~~~~~~

Expression view can be configured to your liking. There are 5 pre-configured views to choose from:

Console
.......

.. image:: images/expression-evaluator/console.png
   :width: 100%
   :alt: Console

Console and tree on bottom
..........................

.. image:: images/expression-evaluator/console-and-tree-on-bottom.png
   :width: 100%
   :alt: Console and tree on bottom

Console and tree on right (defualt)
...................................

.. image:: images/expression-evaluator/console-and-tree-on-right.png
   :width: 100%
   :alt: Console and tree on right (defualt)

Input and tree on bottom
........................

.. image:: images/expression-evaluator/input-and-tree-on-bottom.png
   :width: 100%
   :alt: Input and tree on bottom

Input and tree on right
.......................

.. image:: images/expression-evaluator/input-and-tree-on-right.png
   :width: 100%
   :alt: Input and tree on right

Preferences
~~~~~~~~~~~

In context menu ``Text clear after evaluation`` and ``line numbers`` can be enabled/disabled:

.. image:: images/expression-evaluator/context-menu.png
   :alt: Context menu

More settings can be configured in preferences view (``Window → Preferences → Scala → Debug → Expression Evaluator``):

.. image:: images/expression-evaluator/preferences.png
   :alt: Expression evaluator preferences

Choosing frame on which to evaluate expression
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Expressions can be evaluated on any (non-native) frame in current thread. Frames can be changed by simply clicking on
them in ``Debug`` view:

.. image:: images/expression-evaluator/choosing-frames.png
   :alt: Choosing frames to evaluate

Refreshing variables view after evaluation
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Variables view is refreshed after each evaluation to reflect (possible) changes in values shown. You can disable it in
preferences.

Conditional breakpoints
-----------------------

Expressions can be assigned to breakpoint as a condition. Such breakpoint will only stop if expression evaluates to
``true`` or if evaluation completes with error.

To convert standard breakpoint to conditional one enter ``Breakpoint properties`` either via context menu or
``CTRL + Double click`` shortcut:

.. image:: images/expression-evaluator/conditional-menu.png
   :alt: Access to breakpoint settings

In settings, enable ``conditional`` and type in expression.

.. note:: Condition must return ``Boolean``, otherwise an error will be issued.

.. image:: images/expression-evaluator/conditional-properties.png
   :width: 100%
   :alt: Breakpoint settings

.. note:: Currently only ``Suspend when 'true'`` is supported, ``Suspend when value changes`` is not.

Features and problems
---------------------

Scala is a language with a lot of features and not every feature is supported in expression evaluator right now.
The following should work fine:

* access to all values visible from breakpoint (both locally and globally) with exception of access modifiers (see known limitations),
* assignment to Scala ``var``\s,
* control structures: ``if/else``, ``while``, ``do/while`` and ``for`` comprehensions,
* both Java and Scala var-arg methods and Scala var-arg constructors,
* implicits,
* methods from ``this`` and ``super``,
* number, tuple, unit and ``null`` literals,
* creating new instances (those are created on debugged jvm),
* limited support for lambdas (you need to specify types of arguments sometimes),
* operations on ``Array``\s,
* access to Java fields and methods (both static and instance),
* assignment to Java fields,
* ``isInstanceOf`` and ``asInstanceOf``,
* named-parameters,
* and default values.

Expression evaluator depends on implementation details of Scala, JDI and JVM platform and not all Scala features are
supported yet. Known limitations includes:

* assignment to local variables (those inside methods) of type boxed Java primitive (eg: ``java.lang.Integer``),
* ``try/catch``,
* ``throw``,
* ``scala.Dynamic``,
* Java constructors with var-args (``ToolBox`` fails to compile those - see SI-9212_),
* relative imports (see SI-6393_),
* access to ``private`` and ``protected`` methods and fields,
* float literals (for some reason ``1.0f`` becomes ``1.0`` after parsing with ``ToolBox``),
* and probably more unknown to us.

.. _SI-6393: https://issues.scala-lang.org/browse/SI-6393
.. _SI-9212: https://issues.scala-lang.org/browse/SI-9212