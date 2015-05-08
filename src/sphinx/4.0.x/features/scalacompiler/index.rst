.. include:: /global_defs.hrst

Scala Scopes Building |new| (since 4.1)
==========================================

Scala IDE allows for two different ways of project build. In first one all sources in project are seen and processed by compiler regardless where their are stored and what their output folders are (this is how up to now the project build has been implemented).
In the second, the project build process is broken up into stages called scopes.

Scope is a set of sources which is compiled together and a product of compilation is stored in specific for this set output folder.

This way of project build has been designed to
solve problem of **macro** compilation. Up to now **macro** code has to be moved to separate project which the main project depends on. With Scala Scopes Building **macros** can be defined and used in the same project.

There are defined 3 scopes by convention:

- ``macros`` with default source path: <project root>/src/macros* and especially for Play projects <project root>/conf*
- ``tests`` with default source path: <project root>/src/test* and (because of Play projects) <project root>/test*
- ``main`` which gets all source paths which don't match macros and tests scopes
\ :sub:`(\* is just a wildcard so for example to tests scope are assigned sources from folders src/test, src/test-my-special)`

The scopes are compiled in order: ``macros``, ``main``, ``tests``. The compilation is conditional so if any compilation scope fails then further scopes are not
compiled. So when ``macros`` compilation fails then neither ``main`` nor ``tests`` is compiles. When ``macros`` compiles successfully and ``main`` fails then ``tests`` scope is not compiled.

Of course the output folder of particular scope is added to classpaths of depending on scopes. So that output of ``macros`` is added to ``main`` and ``tests`` scopes classpaths and output of ``main`` is added to ``tests`` classpath.

There is defined across projects scopes dependency too. When given eclipse project (let name it *B*) depends on other project (let call it *A*) then there are introduced dependencies between
projects' scopes in following way:

- ``macros`` of *B* depends on ``macros`` and ``main`` of *A* (so ``macros`` of *B* requires a successful compilations of ``macros`` and ``main`` scopes in project *A*)
- ``main`` of *B* depends on ``macros`` and ``main`` of *A*
- ``tests`` of *B* depends on ``macros``, ``main`` and ``tests`` of *A*

Setting scopes compilation
--------------------------

Project build by scopes is turn ``on`` by default. If you want to suppress it you need to:

- open project context menu and select path ``Properties → Scala Compiler → Build manager``
- uncheck ``useScopesCompiler``, click *Ok* button

  .. image:: images/uncheck-scopes-compilation.png
     :scale: 50 %

- clean project

Changing existing project
-----------------------------------

If project already exists you can change its configuration to get advantage of scopes compilation and resolve problems with dependent builds in following way:

- open project context menu and select path ``Properties → Java Build Path → Source``

- check ``Allow output folders for source folders``

  .. image:: images/allow-output-folders.png
     :scale: 50 %
     
- edit ``Output folder`` of chosen source folder and set your output folder. Click *Ok*.

  .. image:: images/set-output-folder.png
     :scale: 50%
     
- clean project
