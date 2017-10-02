.. Copyright (c) 2015 Contributor. All rights reserved.

.. include:: /global_defs.hrst

Expression evaluator |new|
==========================

Scala debugger uses new expression evaluator, which translates user code into invocations of ``JDI`` remote calls. This allows evaluation of expression in context of some breakpoint, with access to local variables and methods.

.. _expression-evaluator:

Expression evaluation
---------------------

Overview
~~~~~~~~

Main entry point into expression evaluation in Scala IDE debugger is ``ExpressionManager`` object which takes care of holding debug session state, and evaluating expressions in GUI friendly way.

Expression evaluation is done in ``ExpressionEvaluator`` which parses the code using ``ToolBox``, applies several transformations to it and then compiles the result with ``ToolBox``.

Transformation phases
~~~~~~~~~~~~~~~~~~~~~

Before evaluation multiple AST transformations (called ``phases``) are performed.

Those are split into 3 groups:

* First few of them are necessary to make user-defined code type-check. Unbound variables, lambdas, usages of ``this`` and ``super`` etc. are mocked.
* Next comes type-check (from ``ToolBox``), which expands implicits, assigns types and make some other Scala magic.
* After type-check code is transformed further to allow remote execution. All mocks and method calls are replaced with code mimicking their behavior using ``JDI`` calls to debugged jvm.

JdiProxy
~~~~~~~~

Every type used in evaluated expression is replaced with ``JdiProxy``. Those proxies uses ``scala.Dynamic`` trait to intercept all method calls and proxy them to debugged jvm.

Because of different handling of arrays, primitives and ``String``\s on jvm, we had to create a separate proxy flavors for each of those.

JdiContext
~~~~~~~~~~

All methods related to debugged jvm are encapsulated within a ``JdiContext`` instance. It provides abilities to:

* run methods on debugged jvm,
* access types and values in context of debugged jvm,
* load new classes on debugged jvm,
* create proxies for values and object by name,
* create String representations of proxies etc.

Conditional breakpoints
-----------------------

Support for conditional breakpoints is placed in ``ConditionManager`` and relies on ``ExpressionManager`` to do all heavy lifting.