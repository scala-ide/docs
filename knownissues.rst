Known Issues
============

.. topic:: Red screen of death (red squigglies everywhere)

   The number one cause of nothing works is a mismatch between the Scala version of the Eclipse plugin and your project's. Make sure there is only one version of the Scala library on your classpath, and that it matches the version provided by Scala IDE.
   The prime suspect is Maven Dependencies, which can download and add an incompatible scala-library.jar.

   The classpath validator added in Scala IDE 2.0.0 should detect this problem and provide meaning full problem markers.

*[luc] page creation in progress. Content from https://scala-ide-portfolio.assembla.com/spaces/scala-ide/wiki/Known_Issues this need to be copied over*

