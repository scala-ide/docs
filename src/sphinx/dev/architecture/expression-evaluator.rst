.. Copyright (c) 2014 Contributor. All rights reserved. This program and the accompanying materials
   are made available under the terms of the Scala License which accompanies this distribution, and
   is available at http://www.scala-lang.org/node/146

.. _expression-evaluator:

Expression evaluator
====================

Scala debugger uses new expression evaluator, which translates user code into invocations of ``JDI`` remote calls. This allows evaluation of expression in context of some breakpoint, with access to local variables and methods.

Expression evaluation
---------------------

Overview
~~~~~~~~

Main entry point into expression evaluation in Scala IDE debugger is ``ExpressionManager`` object, which is initialized from ``ScalaDebugger`` during debug and takes care of holding debug session state, and evaluating expressions in GUI friendly way.

Expression evaluation is done in ``ExpressionEvaluator`` which parses the code using ``ToolBox``, applies several transformations to it and then compiles the result with ``ToolBox``.

Transformation phases
~~~~~~~~~~~~~~~~~~~~~

Before evaluation multiple AST transformations (called ``phases``) are performed.

First few of them are necessary to make user-defined code type-check:

* code is packed into function from :ref:`expression-evaluator_jdi-context` to :ref:`expression-evaluator_jdi-proxy`,
* unbound variables are mocked,
* usages of ``this`` are mocked,
* and lamba bodies are replaced with stubs of correct types.

Next comes type-check (from ``ToolBox``), which expands implicits, assign types and make some other Scala magic.

After type-check code is transformed further to allow remote execution:

* all used variables and objects are implemented using :ref:`expression-evaluator_jdi-proxy`,
* constructor calls are proxied to create new instances on debugged jvm,
* bodies of lambdas are compiled and bytecode is loaded on debugged jvn,
* conditional expressions are changed to work with proxies,
* same with toString invocations,
* literals and constants are proxied,
* and all mocks from earlier stages are replaced with concrete implementation.

After that code is compiled and result is a function ``JdiContext => JdiProxy``.

Each phase have access to ``TypesContext``, which is mutable, append-only structure which stores information about types encountered during transformation for further processing.

.. _expression-evaluator_jdi-proxy:

JdiProxy
~~~~~~~~

Every unbound variable used in evaluated expression is replaced with instance of ``JdiProxy``. Those proxies uses ``scala.Dynamic`` trait to intercept all method calls and proxy them to debugged jvm.

Because of synthetic methods on primitive types, boxed types and ``java.lang.String`` we had to create a separate proxy flavors for each of them.

* Numeric addition, subtraction, multiplication, division and modulo are aggregated under ``NumericOperations``.
* Bitwise OR, AND and XOR are aggregated under ``BitwiseOperations``.
* Comparison (<, >, <= and >=) is implemented in ``BooleanComparison`` and ``NumericComparison``.
* Logical operations (for Boolean proxies) are implemented in ``LogicalOperations``.

All above sits in package ``org.scalaide.debug.internal.expression.proxies.primitives``.

.. _expression-evaluator_jdi-context:

JdiContext
~~~~~~~~~~

All methods related to debugged jvm are encapsulated within a ``JdiContext`` instance. It provides abilities to:

* run methods on debugged jvm,
* access types and values in context of debugged jvm,
* load new classes on debugged jvm,
* create proxies for values and object by name,
* and create String representations of proxies.

Conditional breakpoints
-----------------------

Support for conditional breakpoint is placed in ``ConditionManager`` and relies on ``ExpressionManager`` to do all heavy lifting.