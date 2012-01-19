Roadmap
=======

Scala IDE for Eclipse - Helium
----------------------------------

It is an ambitious plan, specially for milestone 1. The idea being to work on the most complex change first, the Scala model, and start on other items, so they can be refined by the time of the final release.

The targeted roadmap, *with is the name of the lead contributor(s) for each item*:

**Milestone 1** - mid-March 2012

* Semantic highlighting - *Matt Russell* - `#1000591`__
* Move to Eclipse Indigo - *Typesafe team* - `#1000852`__ |done|
* Design and implement a Scala model - *Typesafe team* - `#1000861`__
* Create an abstraction layer with the Scala compiler - *Typesafe team* - `#1000862`__
* Use the Scala model for the Outline view - *Typesafe team* - `#1000863`__
* Smoother step over/in in the debugger - *Typesafe team* - `#1000864`__
* Create a worksheet (like a REPL but in an editor) - *Typesafe team* - `#1000865`__
* Create a new JDT model builder, using the compiled classes - *Typesafe team* - `#1000866`__
* Extract the presentation compiler in its own project - *Typesafe team* - `#1000867`__

__ http://www.assembla.com/spaces/scala-ide/tickets/1000591
__ http://www.assembla.com/spaces/scala-ide/tickets/1000852
__ http://www.assembla.com/spaces/scala-ide/tickets/1000861
__ http://www.assembla.com/spaces/scala-ide/tickets/1000862
__ http://www.assembla.com/spaces/scala-ide/tickets/1000863
__ http://www.assembla.com/spaces/scala-ide/tickets/1000864
__ http://www.assembla.com/spaces/scala-ide/tickets/1000865
__ http://www.assembla.com/spaces/scala-ide/tickets/1000866
__ http://www.assembla.com/spaces/scala-ide/tickets/1000867

**Milestone 2** - mid-May 2012

* Add support for Find References - *Typesafe team* - `#1000868`__
* Create a real Scala Editor (with all completion, indentation, formatting, ... support) - *Typesafe team* - `#1000860`__
* Improve dependency tracking - *Typesafe team* - `#1000869`__

__ http://www.assembla.com/spaces/scala-ide/tickets/1000868
__ http://www.assembla.com/spaces/scala-ide/tickets/1000860
__ http://www.assembla.com/spaces/scala-ide/tickets/1000869

**Milestone 3** - mid-June 2012

* Add support for Type Hierarchy (both in JDT and Scala specific) - *Typesafe team* - `#1000870`__
* Scala Search (implicit use) - *Typesafe team* - `#1000871`__
* Add support Call Hierarchy - *Typesafe team* - `#1000872`__
* Finalize the API in Scala IDE - *Typesafe team* - `#1000873`__

__ http://www.assembla.com/spaces/scala-ide/tickets/1000870
__ http://www.assembla.com/spaces/scala-ide/tickets/1000871
__ http://www.assembla.com/spaces/scala-ide/tickets/1000872
__ http://www.assembla.com/spaces/scala-ide/tickets/1000873

**Not aligned**

* Specs2 support - *Eric Torreborre?*
* More refactoring support - *Mirko Stocker, Michael Holzer*
* Implicit highlighting - 

.. role:: raw-html(raw)
   :format: html

.. |done| replace:: :raw-html:`<span class="label success">done</span>`
