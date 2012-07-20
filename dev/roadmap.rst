..  role:: strikethrough

.. _roadmap:

Roadmap
=======

Helium (upcoming major release)
----------------------------------

It is an ambitious plan, specially for milestone 1. The idea being to work on the most complex change first, the Scala model, and start on other items, so they can be refined by the time of the final release.

The targeted roadmap, *with the name of the lead contributor(s) for each item*:

**Milestone 1** - April 2012

* Move to Eclipse Indigo - *Eric Molitor* - `#1000852`__ |done|
* `Implicit highlighting`__ - *Jin Mingjian, David Bernard and Mirko Stocker* - `#1000628`__ |done|
* `Semantic highlighting`__ - *Matt Russell* - `#1000591`__ |done|
* `Move Refactoring`__ - *Mirko Stocker* - |done|
* `Improved logging`__ - *Typesafe Team* - `#1000880`__ - |done| 
* Design and implement a Scala model - *Typesafe team* - `#1000861`__ - |done|
* :strikethrough:`Create an abstraction layer with the Scala compiler` - *Typesafe team* - `#1000862`__ - (Moved to Lithium)
* `Smoother step over/in in the debugger`__ - *Typesafe team* - `#1000864`__ - |done|
* :strikethrough:`Extract the presentation compiler in its own project` - *Typesafe team* - `#1000867`__ (Reconsidered: The Presentation Compiler is too coupled to the compiler.)

__ http://www.assembla.com/spaces/scala-ide/tickets/1000852
__ http://scala-ide.org/docs/helium/features/implicit-highlighting/index.html
__ http://www.assembla.com/spaces/scala-ide/tickets/1000628
__ http://scala-ide.org/docs/helium/features/semantic-highlighting/index.html
__ http://www.assembla.com/spaces/scala-ide/tickets/1000591
__ http://scala-ide.org/docs/helium/features/moverefactoring.html
__ http://scala-ide.org/docs/helium/features/logging.html
__ http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000880
__ http://www.assembla.com/spaces/scala-ide/tickets/1000861
__ http://www.assembla.com/spaces/scala-ide/tickets/1000862
__ http://scala-ide.org/docs/helium/features/scaladebugger/index.html
__ http://www.assembla.com/spaces/scala-ide/tickets/1000864
__ http://www.assembla.com/spaces/scala-ide/tickets/1000867

**Milestone 2** - end of July 2012

* ScalaTest support - *Chee Seng Chua, Bill Venners* - |progress| `(watch the demo!)`__
* `Implicit hyperlinking`__ - *Typesafe Team* - `#10001002`__ - |done|
* Infer Type of Structured Selection - *Typesafe Team* - |done|
* Create a worksheet (like a REPL but in an editor) - *Typesafe team* - `#1000865`__ |progress|
* Add support for Find References - *Typesafe team* - `#1000868`__ - |progress|
* Improve dependency tracking - *Typesafe team* - `#1000869`__
* Scala 2.10 support (e.g., macros) -  *Typesafe team* |done|
* `New Scala debugger`__ - *Typesafe team* - |progress|
* `New refactorings`__ - *Michael Holzer, Mirko Stocker* - `#1000960`__ - |done|
* `Source generators`__ - *Michael Holzer* - `#1001018`__ - |done|

__ http://skillsmatter.com/podcast/scala/scalatest-scalamock-subcut
__ http://scala-ide.org/docs/helium/features/implicit-hyperlinking/index.html
__ http://www.assembla.com/spaces/scala-ide/tickets/1001002
__ http://www.assembla.com/spaces/scala-ide/tickets/1000862
__ http://www.assembla.com/spaces/scala-ide/tickets/1000863
__ http://www.assembla.com/spaces/scala-ide/tickets/1000865
__ http://www.assembla.com/spaces/scala-ide/tickets/1000868
__ http://www.assembla.com/spaces/scala-ide/tickets/1000869
__ http://scala-ide.org/docs/helium/features/scaladebugger/index.html
__ http://www.assembla.com/spaces/scala-ide/tickets/1000629
__ http://scala-ide.org/docs/helium/features/new-refactoring/index.html
__ http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000960
__ http://scala-ide.org/docs/helium/features/source-generators/index.html
__ http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001018

**Helium release**

* Eclipse Juno (4.2) support - *Eric Molitor* - Milestone 2? |progress|
* Switch to vanilla Sbt (0.13) - |progress|
* Common update site for Scala plugins - |progress|



Lithium (next major release)
-------------------------------

* Create an abstraction layer with the Scala compiler - *Typesafe team* - `#1000862`__ 
* Use the Scala model for the Outline view - *Typesafe team* - `#1000863`__
* Create a real Scala Editor (with all completion, indentation, formatting, ... support) - *Typesafe team* - `#1000860`__
* Add support for Type Hierarchy (both in JDT and Scala specific) - *Typesafe team* - `#1000870`__
* Scala Search (implicit use) - *Typesafe team* - `#1000871`__
* Add support Call Hierarchy - *Typesafe team* - `#1000872`__
* Finalize the API in Scala IDE - *Typesafe team* - `#1000873`__

__ http://www.assembla.com/spaces/scala-ide/tickets/1000860
__ http://www.assembla.com/spaces/scala-ide/tickets/1000870
__ http://www.assembla.com/spaces/scala-ide/tickets/1000871
__ http://www.assembla.com/spaces/scala-ide/tickets/1000872
__ http://www.assembla.com/spaces/scala-ide/tickets/1000873

**Not aligned**

* Code Analysis - *Mirko Stocker* - `#1000629`__
* Create a new JDT model builder, using the compiled classes - *Typesafe team* - `#1000866`__
* Specs2 support - *Richard Oliver Legendi, Eric Toreborre* (GSoC)
* Integrate a SBT-console - *Sandro Grzicić* (GSoC)
* Smart Quick Fixes - *Ivan Kuraj* (GSoC)

__ http://www.assembla.com/spaces/scala-ide/tickets/1000866

.. role:: raw-html(raw)
   :format: html

.. |done| replace:: :raw-html:`<span class="label success">done</span>`

.. |progress| replace:: :raw-html:`<span class="label warning">in progress</span>`

.. |testing| replace:: :raw-html:`<span class="label info">testing</span>`
