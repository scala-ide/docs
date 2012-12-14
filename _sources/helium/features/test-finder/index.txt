Scala JUnit Test finder
=======================

The plain JUnit test runner of Eclipse works well for Scala projects as well. If one does not need (or cannot use) a Scala-specific testing framework such as `ScalaTest <http://www.scalatest.org>`_ (that comes with `its own Eclipse plugin <http://www.scalatest.org/user_guide/using_scalatest_with_eclipse>`_), JUnit is a good default. In this release we added a specific test finder for Scala projects (the default Java one was unreliable, and often missed Scala classes that define unit tests).

To enable the new test finder simply select **Scala JUnit 4** from the drop down list of test runners in the usual *JUnit run configuration*.


.. image:: images/config.png

The `Scala JUnit 4` runner is automatically selected when **right-clicking** on a file (package, project or source folders are also supported) if the project has the Scala nature.

.. image:: images/right-click.png