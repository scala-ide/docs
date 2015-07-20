.. include:: /global_defs.hrst

.. _roadmap:

Roadmap
=======

In the following there is an incomplete list about features we at least intend to implement for upcoming Scala IDE versions. Additional features are always possible, beside from performance improvements and bug fixes.

4.2.0 (end of July)
-------------------

- Async debugger: The new tool for control flow tracking in your cutting edge reactive applications. It is the must for every developer who needs to trace control flow in existing and newly built applications where Scala Future or Akka Actors are playing a key role. This debugger improvement allows you to stop at breakpoints and find the trigger of the suspicious event when it was sent from a different thread.
- Auto edits: Whenever you type, the editor supports you by adding missing parentheses, by indenting the code correctly for you and by doing a lot of other little actions that save you keystrokes. Unfortunately, not everyone agreed with the actions that were done and sometimes there were not even the correct actions done. Auto edits solve this problem by making everything configurable - if something doesn't work in the way how you would expect it you can just change it. Beside from that new features like automatic template completion are going to be added.
- Scala 2.12 support: A new major version of Scala is coming and Scala IDE should of course be able to handle it well.

4.3.0 (end of October)
----------------------

- sbt-server integration: This is a long awaited feature. Every user of Play or Scala.js projects has the problem that not all IDE features work reliable on their codebase because internally the IDE uses its own builder to build the application, whereas it really should use the builder of your project. sbt is the builder of choice of Scala users and sbt-server makes it possible to remotely control it by the IDE. By integrating sbt-server into Scala IDE all problems that arise from bytecode inequality that comes from multiple builds fighting each other should be gone.

  Beside from the sbt-server integration, Scala IDE will provide an editor for ``*.sbt`` files and it will even be possible to fully handle the Scala code in your ``project/`` directory. Furthermore you will no longer need the ``sbteclipse`` plugin due to the fact that the IDE will be able to automatically import your sbt projects and track changes to them in the background and apply them silently to your working environment.

