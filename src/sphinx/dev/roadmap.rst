.. include:: /global_defs.hrst

.. _roadmap:

Roadmap
=======

Goal
----

The central goal until the end of 2014 is to improve Play experience in Eclipse. To that end, we need some infrastructure work: improve Scala IDE core to work with different versions of Scala (since Play does not switch at the same time a new Scala is released) and sbt server (for correctly building a Play project).


4.0.0 - codename Lithium
------------------------

Milestone 2 (end of May)
~~~~~~~~~~~~~~~~~~~~~~~~

- Support multiple major Scala versions in the same Eclipse IDE (2.10 and 2.11). This feature allows people to have only one working installation of Eclipse, but mix 2.10 and 2.11 projects in the same workspace. It's been a long-requested feature, implemented based on the new source-flag added in the Scala compiler 2.11.0:

   - use the newer type-checker (presentation compiler) in the compatibility mode (passing -Xsource:2.10)
   - use the exact compiler for building (either 2.10 or 2.11), based on project configuration

Milestone 3 (middle of July)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

- `Eclipse Luna <https://projects.eclipse.org/releases/luna>`_ support
- clean up sdt.core API. The core Scala IDE is already being used by plugins (such as ScalaTest, Play or Worksheet), but it does not have a stable API. This work item cleans up existing functionality and offers a semantically-versioned interface for plugin writers.
- community contributions

RC1 (middle of August)
~~~~~~~~~~~~~~~~~~~~~~

- bugfixes in new features, integrate community contributions

Play IDE
--------

The main missing piece in the Play IDE experience is the builder. Play projects have a relatively complex build, involving asset compilers (routes, templates, CoffeeScript, etc.) or source generators. Instead of replicating the sbt build, we will build an sbt-based builder that delegates to an out-of-process Sbt server.

- switch Play to Twirl (together with Lithium M3, middle of July )

0.5.0 (middle of September)
~~~~~~~~~~~~~~~~~~~~~~~~~~~

- sbt server support
	- Import Sbt project (done) - 0.1.0
	- Builder by delegating to Sbt server - (0.2.0 Sept 8)
	- getting errors/warnings, acting on changed files


1.0 (end of the year)
~~~~~~~~~~~~~~~~~~~~~

- launching and debugging support for Play apps
- better completion in templates
- report errors as you type in routes


Community
---------

In addition to the Typesafe team there is a very active community around the Scala IDE, who constantly donate their time and energy to build great features. If you are working on something and want to feature on the roadmap, please let us know!

Simon Schäfer
~~~~~~~~~~~~~~

For GSoC14 there are several improvements in work. They include inprovements to the user experience and the starting transition to an "user extensible IDE". User extensible means that functionality of the IDE can be extended by users with there own moduls, which are written entirely in Scala.

Improvement to the user experience include:

- Better wizards and dialogs
- Cleaned up hovers, icons, menu bars, error markers etc.
- Behavior of the Scala IDE follows Scala preferences and not Java preferences

New features should be implemented or reimplemented in so far that it is possible to extend them by users. This include:

- Save actions. At the moment there are only the save actions of the Java editor available, which mostly do not work and/or produce error messages when they are applied on Scala code.
- Auto edits. They do automatic changes to a document while the user types and are used to do small minor code edits, which developers normally don't want to do by themselves. This includes features such as automatic indentation and auto expanding multi line comments.
- Refactorings. They are the big brother of auto edits, need to be invoked explicitly and can do even the most powerful code changes. Giving users the possibility to write them on their own means that they can automate their repetitive code changes much more easily.

The full GSoC proposal, can be found `here <http://www.google-melange.com/gsoc/proposal/public/google/gsoc2014/sschaef/5629499534213120>`_.



