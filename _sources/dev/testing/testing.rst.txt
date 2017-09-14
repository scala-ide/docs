.. _testing_regression-test-suite:

Regression Test Suite
=====================

The Scala IDE relies for most of its functionality on two pillars:

.. toctree::
  :maxdepth: 1

  eclipse-tests
  presentation-compiler-tests

Testing should therefore be directed to the two of them, but also to their integration. 


While unit tests make sense, it is sometimes difficult to test things in isolation, especially when 
they involve the Scala compiler. Imagine a test for code completion: there must be a source file, a 
position inside the source file, an instantiated Scala PC, a correct classpath. For hyperlinking, it 
may be even worse: in addition to the above, you’d need to have a source locator (in order to find 
attached sources to a jar file). Of course, you could mock most of these things, but that would be 
very tedious. The alternative is to use real projects and an Eclipse instance, where this 
functionality can be tested programmatically. We call them integration tests, because they exercise 
a full Eclipse installation, and mimic very much what the user is doing.

.. note:: 

	All tests have to run without a UI (headless mode). If any UI class is instantiated, the test 
	will fail in Jenkins (the slaves do not have a GUI). Make sure that any test you write does not 
	depend on UI classes, and always check that the test runs from the command line.

.. note:: 

	The presentation compiler test suite is part of the `Scala project source tree 
	<https://github.com/scala/scala/tree/master/src/compiler/scala/tools/nsc/interactive/tests>`_. 