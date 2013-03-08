.. include:: /global_defs.hrst

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

**Milestone 2** - August 2012 :strikethrough:`end of July`

* `ScalaTest support`__ - *Chee Seng Chua, Bill Venners* - |done| `(watch the demo!)`__
* `Implicit hyperlinking`__ - *Typesafe Team* - `#10001002`__ - |done|
* `Infer Type of Structured Selection`__ - *Typesafe Team* - |done|
* `Create a worksheet (like a REPL but in an editor)`__ - *Typesafe team* - `#1000865`__ |done|
* Initial support for Find References - *Typesafe team* - `#1000868`__ - |done|
* Scala 2.10 support (e.g., macros, string interpolation) -  *Typesafe team* |done|
* `New Scala debugger`__ - *Typesafe team* - |done|
* `New refactorings`__ - *Michael Holzer, Mirko Stocker* - `#1000960`__ - |done|
* `Source generators`__ - *Michael Holzer* - `#1001018`__ - |done|
* Switch to vanilla Sbt (0.13) - |done|
* `Common update site for Scala plugins (a.k.a, Scala IDE Ecosystem)`__ - |done|

__ http://scala-ide.org/blog/scalatest-ecosystem.html
__ http://skillsmatter.com/podcast/scala/scalatest-scalamock-subcut
__ http://scala-ide.org/docs/helium/features/implicit-hyperlinking/index.html
__ http://www.assembla.com/spaces/scala-ide/tickets/1001002
__ http://scala-ide.org/docs/helium/features/show-type.html
__ http://github.com/dragos/scala-worksheet
__ http://www.assembla.com/spaces/scala-ide/tickets/1000865
__ http://www.assembla.com/spaces/scala-ide/tickets/1000868
__ http://scala-ide.org/docs/helium/features/scaladebugger/index.html
__ http://scala-ide.org/docs/helium/features/new-refactoring/index.html
__ http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000960
__ http://scala-ide.org/docs/helium/features/source-generators/index.html
__ http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001018
__ http://scala-ide.org/download/ecosystem.html

**Milestone 3** - December 2012

* Hardening the Scala debugger |progress|

**Helium release**

* Eclipse Juno (4.2) support - *Eric Molitor* - `#1000988`__ |progress|
* Performance improvements in the editor (`#1001156`__)

__ http://www.assembla.com/spaces/scala-ide/tickets/1000988
__ https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001156

**Not aligned**

* Code Analysis - *Mirko Stocker* - `#1000629`__
* Create a new JDT model builder, using the compiled classes - *Typesafe team* - `#1000866`__
* `Specs2 support - *Richard Oliver Legendi, Eric Toreborre* (GSoC)`__
* `Integrate a SBT-console - *Sandro Grzicić* (GSoC)`__
* `InSynth`__ & Smart Quick Fixes - *Ivan Kuraj* (GSoC)

__ http://www.assembla.com/spaces/scala-ide/tickets/1000629
__ http://www.assembla.com/spaces/scala-ide/tickets/1000866
__ https://github.com/rlegendi/specs2-runner
__ https://github.com/SandroGrzicic/sbtconsole
__ https://github.com/kaptoxic/scala-ide-insynth-integration/

Lithium (next major release)
-------------------------------

* Full support for Find References - *Typesafe team* - `#1000868`__
* Create an abstraction layer with the Scala compiler - *Typesafe team* - `#1000861`__, `#1000862`__
* Use the Scala model for the Outline view - *Typesafe team* - `#1000863`__
* Create a real Scala Editor (with all completion, indentation, formatting, ... support) - *Typesafe team* - `#1000860`__
* Add support for Type Hierarchy (both in JDT and Scala specific) - *Typesafe team* - `#1000870`__
* Scala Search (implicit use) - *Typesafe team* - `#1000871`__
* Add support Call Hierarchy - *Typesafe team* - `#1000872`__
* Finalize the API in Scala IDE - *Typesafe team* - `#1000873`__

__ http://www.assembla.com/spaces/scala-ide/tickets/1000868
__ http://www.assembla.com/spaces/scala-ide/tickets/1000861
__ http://www.assembla.com/spaces/scala-ide/tickets/1000862
__ http://www.assembla.com/spaces/scala-ide/tickets/1000863
__ http://www.assembla.com/spaces/scala-ide/tickets/1000860
__ http://www.assembla.com/spaces/scala-ide/tickets/1000870
__ http://www.assembla.com/spaces/scala-ide/tickets/1000871
__ http://www.assembla.com/spaces/scala-ide/tickets/1000872
__ http://www.assembla.com/spaces/scala-ide/tickets/1000873
