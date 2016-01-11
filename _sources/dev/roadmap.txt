.. include:: /global_defs.hrst

.. _roadmap:

Roadmap
=======

The central goal is to improve developer productivity by providing Eclipse based IDE with Scala specific features. We aim also at creating a tool supporting development of Reactive applications.
Eclipse is the open platform of our choice but there are other IDE projects in the open-source space and we would like to contribute to them in one way or the other too by sharing ideas or even parts of the code where possible.

In the following there is an incomplete list of features we intend to implement for upcoming Scala IDE revisions. Besides the performance improvements and bug fixes there is always a place for additional features and improvements. We encourage everyone to make suggestions via the `Scala IDE mailing list <https://groups.google.com/forum/#!forum/scala-ide-user>`_ or our `issue tracker <https://www.assembla.com/spaces/scala-ide/support/tickets>`_.

4.4.0
-----

- sbt-server integration: This is a long awaited feature. Every user of Play or Scala.js projects has the problem that not all IDE features work reliable on their codebase because internally the IDE uses its own builder to build the application, whereas it really should use the builder of your project. sbt is the builder of choice of Scala users and sbt-server makes it possible to remotely control it by the IDE. By integrating sbt-server into Scala IDE all problems that arise from bytecode inequality that comes from multiple builds fighting each other should be gone.

  Beside from the sbt-server integration, Scala IDE will provide an editor for ``*.sbt`` files and it will even be possible to fully handle the Scala code in your ``project/`` directory. Furthermore you will no longer need the ``sbteclipse`` plugin due to the fact that the IDE will be able to automatically import your sbt projects and track changes to them in the background and apply them silently to your working environment.

