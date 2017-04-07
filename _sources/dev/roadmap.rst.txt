.. include:: /global_defs.hrst

.. _roadmap:

Roadmap
=======

The central goal is to improve developer productivity by providing Eclipse based IDE with Scala specific features. We aim also at creating a tool supporting development of Reactive applications.
Eclipse is the open platform of our choice but there are other IDE projects in the open-source space and we would like to contribute to them in one way or the other too by sharing ideas or even parts of the code where possible.

In the following there is an incomplete list of features we intend to implement for upcoming Scala IDE revisions. Besides the performance improvements and bug fixes there is always a place for additional features and improvements. We encourage everyone to make suggestions via the `Scala IDE mailing list <https://groups.google.com/forum/#!forum/scala-ide-user>`_ or our `issue tracker <https://www.assembla.com/spaces/scala-ide/support/tickets>`_.

4.4.0
-----

Enhancements and bug fixing:

- debugger backend transition from Scala ``Actors`` to ``Futures`` .

- sbt incremental compiler upgrade to version 0.13.8.

- enhancements in ``scala-refactoring`` focused on Organize Imports mainly but not only.

4.5.0
-----

Coming features:

- sbt project import. Easy way to start working on sbt project with no need to run ``sbt eclipse`` plugin.

- sbt file editor.

- Apache Spark project launcher. Feed with output of project and launch Apache Spark without leaving Scala IDE.

And continuation of bug fixing.