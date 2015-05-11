.. include:: /global_defs.hrst

Scala Scopes Building |new| (since 4.1)
==========================================

Scala IDE allows for two different ways of a project building. The old one takes all sources in project and compiles them regardless where their are stored and what their output folders are.
The second one breaks up a project building into stages called scopes.

Scope is a set of sources which is compiled together and "a product of compilation" is stored in specific for this set output folder.

This feature has been designed to solve a problem of **macro** compilation. Up to now **macro** code has to be moved to separate project which the main project depends on. With Scala Scopes Building **macros** can be defined and used in the same project.

3 scopes are defined by convention:

- ``macros`` with default source path: ``<project root>/src/macros*`` and especially for Play projects ``<project root>/conf*``
- ``tests`` with default source path: ``<project root>/src/test*`` and (because of Play projects) ``<project root>/test*``
- ``main`` which gets all source paths which don't match macros and tests scopes
\ :sub:`(\* is just a wildcard so for example to tests scope are assigned sources from folders src/test, src/test-my-special)`

The scopes are compiled in order: ``macros``, ``main``, ``tests``. The compilation is conditional so if any compilation scope fails then further scopes are not
compiled. So when ``macros`` compilation fails then neither ``main`` nor ``tests`` is compiled. When ``macros`` compiles successfully and ``main`` fails then ``tests`` scope is not compiled.

Of course the output folder of particular scope is added to classpaths of depending scopes. So that output of ``macros`` is added to ``main`` and ``tests`` scopes classpaths and output of ``main`` is added to ``tests`` classpath.

Besides of scopes dependencies in single project there are the dependencies defined across projects' scopes too. When some given project (for simplicity let name it *B*) depends on other project (let call it *A*) then the dependencies between
these two projects' scopes are determined in following way:

- ``macros`` of *B* depends on ``macros`` and ``main`` of *A* (so ``macros`` of *B* requires a successful compilations of ``macros`` and ``main`` scopes in project *A*)
- ``main`` of *B* depends on ``macros`` and ``main`` of *A*
- ``tests`` of *B* depends on ``macros``, ``main`` and ``tests`` of *A*

Enabling scopes compilation
--------------------------

The feature is turned ``on`` by default. To suppress it go to project properties: ``Right click on project → Properties → Scala Compiler → Build manager`` and uncheck ``useScopesCompiler``. Clean your project afterwards.

  .. image:: images/uncheck-scopes-compilation.png
     :scale: 50 %

Changing the settings for existing project
-----------------------------------

If project already exists you can change its configuration to get advantage of scopes compilation and resolve problems with dependent builds in a following way:

- open project context menu and select path ``Right click on project → Properties → Java Build Path → Source``

- check ``Allow output folders for source folders``

  .. image:: images/allow-output-folders.png
     :scale: 50 %
     
- edit ``Output folder`` of chosen source folder and set your output folder. Click *Ok*.

  .. image:: images/set-output-folder.png
     :scale: 50%
     
- clean project
