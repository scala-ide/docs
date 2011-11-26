Getting Started
===============

Requirements
------------

* the Java Development Kit, version 1.5.0 or newer. We recommand the latest 1.6.x version, there have been issues reported when using Eclipse and Java 7.

* Eclipse, including the JDT. Either the "Classic" or "Eclipse for Java Developers" is sufficient.

  * Scala IDE is officially supporting version 3.6.2.

  * versior 3.7.1 is reported to work fine.

Installation
------------

The Scala IDE for Eclipse is best installed (and updated) directly from within Eclipse.

This is done by using ``Help →  Install New Software...``, add the ``Add...`` button in the dialog.

Choose a name for the update site (`Scala IDE` is an obvious choice). Then read the next section to select which version you will install.

Choosing what version to install
................................

`download.scala-ide.org`__ provides the URLs of all available update sites.

All update sites for installation are at http://download.scala-ide.org/. The constellation of versions can be daunting at first. Read this paragraph to make an informed choice.
The Eclipse plugin comes with a Scala compiler and Scala library. In a given Eclipse installation there can be a single version of the Scala plugin for Eclipse, so you need to decide what Scala version you are going to use. In other words, the choice of Scala version (2.8.x, 2.9.x, etc) is not per project, but per Eclipse installation.
Scala IDE for Eclipse supports three versions of the Scala compiler:
2.9.x supports projects using Scala 2.9.1 or 2.9.0-1. This is the currently release version of Scala, so you should choose this if you are unsure.
2.8.x is a backwards compatible version for projects using Scala 2.8.1 or 2.8.2.
2.10 is a build relative to Scala trunk. Choose this if you want the bleeding edge, or working on scalac itself
Once you decided for the Scala version, you need to choose a version of the IDE.
2.0 is a stable branch for the upcoming 2.0 release. We release nightlies and betas. Choose the latest beta for 2.9 if you are unsure what to install.
2.1 is the upcoming 2.1 release. It will be less stable and contain more features.
Check http://download.scala-ide.org/ for all downloads.

__ http://download.scala-ide.org

Using Scala IDE with an existing project
----------------------------------------

You have successfully installed the Scala plugin, and you are eager to try it out on your favorite project. Please take the time to read the following paragraphs, which should help setting up your development environment quickly and without pain.
Important: Before you begin, make sure your Scala sources follow the Java convention for packages.
Each package should appear in its own directory with the same name. Eclipse expects this convention to
be followed when it looks for classes.

Start from an existing SBT project
----------------------------------

SBT is the standard build tool for Scala projects. There are two versions in wide use today: 0.7.x and 0.10+. Both have plugins that can generate Eclipse project files out of the SBT project definition.
If you are using SBT 0.7.x, use eclipsify. Follow the instructions at https://github.com/musk/SbtEclipsify/tree/0.8.0 to install the plugin. You may want to check out the update-sources plugin, which can download attached sources for your dependencies. They allow the IDE to navigate to definitions in those libraries.
If you are using SBT 0.11.x use sbteclipse. Follow the instructions at https://github.com/typesafehub/sbteclipse to install and use the plugin to generate the project definition. sbteclipse can download attached sources natively, so you do not need the update-sources plugin.
Once you have installed and generated the Eclipse project files using one of the above plugins, start Eclipse and Choose File/Import, then choose Existing Project into workspace. Navigate to the directory of your project, and Eclipse should detect the generated project.
 Important: SBT manages the dependency to scala-library.jar. Make sure the version of Scala you use in your SBT project
  matches the version of Scala installed in Eclipse.

Start from an existing maven project
------------------------------------

(please contribute)

Troubleshooting
---------------

*[luc] move everything to know issues*

  Red screen of death (red squigglies everywhere)
  The number one cause of nothing works is a mismatch between the Scala version of the Eclipse plugin and your project's. Make sure there is only one version of the Scala library on your classpath (prime suspect is Maven Dependencies, which can download and add an incompatible scala-library.jar).
  You can also the Known Issues page.

