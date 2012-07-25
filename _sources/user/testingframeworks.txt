Using Unit Testing Frameworks
=============================

Test from multiple unit testing frameworks can be run inside Scala IDE, using the JUnit 4 runners provided by the frameworks

Setting up JUnit 4
------------------

Inside Eclipse
..............

To add JUnit to a project from inside Eclipse, right-click on the project in the package explorer view, select ``Build Path → Add Libraries...``, select ``JUnit``, and then ``JUnit 4`` in the next wizard page.

In sbt
......

To add JUnit in the sbt configuration, add the following dependency in the ``build.sbt`` file:

.. code-block:: scala

   libraryDependencies ++= Seq(
      "junit" % "junit" % "4.8.1" % "test"  
   )

In maven
........

To add JUnit in the maven configuration, add the following dependency in the ``pom.xml`` file:

.. code-block:: xml
   
   <dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.8.1</version>
        <scope>test</scope>
   </dependency>

Using JUnit 4
-------------

To run a test suite using JUnit in Scala IDE, open the source file of the test, right-click in it, and select ``Run as → JUnit Test``.

You can re-run your test using the launch configuration which is automatically created. Check the `Eclipse documentation`__ for more details.

__ http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2FgettingStarted%2Fqs-12.htm

ScalaTest
---------

The `ScalaTest`_ framework provides a runner (``org.scalatest.junit.JUnitRunner``) allowing to use JUnit to run the test suites.

Adding this annotation makes a ScalaTest test suite runnable with JUnit4:

.. code-block:: scala

   @RunWith(classOf[JUnitRunner])

The test suite from the `FunSuite`__ example becomes the following when run in Scala IDE:

__ http://scalatest.org/getting_started_with_fun_suite

.. image:: images/testing-scalatest-01.png
   :width: 100%
   :target: ../_images/testing-scalatest-01.png

Specs
-----

The `Specs`_ framework provides a runner (``org.specs.runner.JUnitSuiteRunner``) allowing to use JUnit to run the suites.

Adding this annotation makes a Specs suite runnable with JUnit4:

.. code-block:: scala

   @RunWith(classOf[JUnitSuiteRunner])

For Specs2, the annotation is the following:

.. code-block:: scala

   @RunWith(classOf[JUnitRunner])

The `HelloWorldSpec`__ example becomes the following when run in the Scala IDE:

__ http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html

.. image:: images/testing-specs-01.png
   :width: 100%
   :target: ../_images/testing-specs-01.png

JUnit
-----

`JUnit`_ test suites can be run directly in Scala IDE.

.. image:: images/testing-junit-01.png
   :width: 100%
   :target: ../_images/testing-junit-01.png

.. _JUnit: http://junit.org
.. _ScalaTest: http://scalatest.org
.. _Specs: http://etorreborre.github.com/specs2/
