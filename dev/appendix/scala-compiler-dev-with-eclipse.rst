Scala compiler development under Eclipse 
========================================


This tutorial will show you how to set up the Scala IDE in Eclipse so you can use it to work on the Scala compiler:

* Download and install the latest stable Eclipse release from eclipse.org.
* Install the Scala IDE.

  + In Eclipse go to Help -> Install New Software ... In "Work with:", put
  + http://download.scala-ide.org/nightly-update-master-trunk (you need the nightly build of the Scala IDE)
  + Click Add. Select All. Finish steps to install.

* Tweak the settings for eclipse.ini as discussed here:  https://www.assembla.com/spaces/scala-ide/wiki/Setup
* Import the Scala project in your workspace:

  + File -> Import ... Existing Projects into Workspace.
  + Pick the Scala trunk as the source directory.
  + Finish.

* Unselect Project -> Build Automatically. (Recommended.)
* Import the libraries in lib/ into the project

  + Project -> Properties.
  + Java Build Path
  + Libraries
  + Add Jars, lib/\*.jar
  + Add Jars again, lib/ant/\*.jar

* Set the correct properties for the source code:

  + Project -> Properties.
  + Java Build Path.
  + Source.
  + Remove all.
  + Add Folder... and select scala/src/compiler, scala/src/fjbg, scala/src/msil.

* There is one error reported in scala/src/compiler/scala/tools/nsc/io/Jar.scala where two possible implicit conversions can take place -- just ignore that :)

Thanks to Hubert and Nada.
