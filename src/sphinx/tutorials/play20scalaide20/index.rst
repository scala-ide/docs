Setup and use Play framework 2.0 in Scala IDE 2.0
=================================================

|frflag| :doc:`version française<index-fr>`

.. |frflag| image:: ../../user/images/frflag.png

What is in this guide?
----------------------

This guide will show you how to configure a Play web application to import it in Scala IDE, how to configure `Scala IDE`_ to work fine with the Play framework and finally how to develop Play web application from inside Scala IDE.

Prerequisites
.............

*   `Eclipse`_ 3.6.2 (Helios) with Scala IDE for Scala 2.9 installed (update site: http://download.scala-ide.org/releases-29/stable/site).

    Check the :ref:`getting started <gettingstarted_getting-started>` page for instructions on how to install Scala IDE.

*   A basic knowledge of the Eclipse user interface is required.

*   No knowledge of the Scala language is required (in this guide).

*   No knowledge of the Play framework is required (in this guide).

Setting up Play 2.0
-------------------

To be able to create a Play web application, the Play framework need to be installed. If you have not installed it already, follow this few steps, or use the `Play documentation`_.

*   Download Play framework 2.0 from http://www.playframework.org/.

*   Unzip it in your preferred location. Let's say ``/path/to/play20`` for the purpose of this document.

*   For convenience, add the Play folder to your system PATH:

    .. code-block:: bash

       export PATH=$PATH:/path/to/play20

Creating a Play 2.0 application
-------------------------------

*   In your development folder, ask Play to create a new web application, as a ``simple Scala application``.

    .. image:: images/play20-scalaide20-01.png
       :alt: play new testApp
       :width: 100%
       :target: ../../_images/play20-scalaide20-01.png

*   Go into the application folder.

    .. image:: images/play20-scalaide20-02.png
       :alt: cd testApp
       :width: 100%
       :target: ../../_images/play20-scalaide20-02.png

*   And launch Play.

    .. image:: images/play20-scalaide20-03.png
       :alt: play
       :width: 100%
       :target: ../../_images/play20-scalaide20-03.png

*   In Play, launch your newly created web application.

    .. image:: images/play20-scalaide20-04.png
       :alt: run
       :width: 100%
       :target: ../../_images/play20-scalaide20-04.png

*   Check that the application works: http://localhost:9000/.

    .. image:: images/play20-scalaide20-05.png
       :alt: running
       :width: 100%
       :target: ../../_images/play20-scalaide20-05.png

Configuring the Play 2.0 web application for Scala IDE
------------------------------------------------------

Now that the Play application is running, it needs to be configured so it can be imported into Scala IDE.

Play 2.0-RC1 integrates `sbteclipse`_, which allow to create configuration files of a project for Eclipse. 

*   First, exit the 'run' mode in Play using ``ctrl-d``.

    .. image:: images/play20-scalaide20-06.png
       :alt: ctrl-d, exit
       :width: 100%
       :target: ../../_images/play20-scalaide20-06.png

*   ``eclipsify`` is the command to invoke sbteclipse in Play.

    .. image:: images/play20-scalaide20-09.png
       :alt: eclipse
       :width: 100%
       :target: ../../_images/play20-scalaide20-09.png

*   Relaunch the web application, in 'auto-reloading' mode, using ``~ run``, so it is running in the background.

    .. image:: images/play20-scalaide20-10.png
       :alt: run
       :width: 100%
       :target: ../../_images/play20-scalaide20-10.png

Configuring Scala IDE for the Play 2.0 web application
------------------------------------------------------

Setting a few preferences in Eclipse will make everything easier to use.

*   Open the internal web browser view in Eclipse, and check you can access your web application.

    .. image:: images/play20-scalaide20-12.png
       :alt: http://localhost:9000/
       :width: 100%
       :target: ../../_images/play20-scalaide20-12.png

*   Configure Eclipse so changes on the file system are automatically picked up.

    .. image:: images/play20-scalaide20-13.png
       :alt: refresh automatically
       :width: 100%
       :target: ../../_images/play20-scalaide20-13.png

*   If you don't have the Web Development Tools for Eclipse installed, Eclipse opens ``.html`` files in a web browser. Configure it to use the ``Scala Editor`` instead.

    .. image:: images/play20-scalaide20-14.png
       :alt: HTML file in text editor
       :width: 100%
       :target: ../../_images/play20-scalaide20-14.png

Importing the Play web application into Scala IDE
-------------------------------------------------

Everything is setup, it is time to import the project in the IDE.

*   Import the Play 2.0 application as an ``Existing Projects into Workspace``.

    .. image:: images/play20-scalaide20-15.png
       :alt: import project
       :width: 100%
       :target: ../../_images/play20-scalaide20-15.png

*   The configuration generated by sbteclipse is working fine in Eclipse, but it is not optimal when using with Scala IDE.

    Add the ``target/src_managed/scala`` folder as a source folder.

    .. image:: images/play20-scalaide20-20.png
       :alt: add the managed sources folder
       :width: 100%
       :target: ../../_images/play20-scalaide20-20.png

*   And remove the ``classes_managed`` folder for the build path.

    .. image:: images/play20-scalaide20-21.png
       :alt: remove managed classes folder
       :width: 100%
       :target: ../../_images/play20-scalaide20-21.png

*   Everything is good, everything compiles.

    .. image:: images/play20-scalaide20-16.png
       :alt: everything compiles
       :width: 100%
       :target: ../../_images/play20-scalaide20-16.png

Doing some development
----------------------

Now that everything is setup, we can start to do some real work.

Let's change the main page to display a quote instead of the default page.

*   First, create the ``models.Quote`` class using the new ``Scala Class`` wizard.

    .. image:: images/play20-scalaide20-19.png
       :alt: create model.Quote
       :width: 100%
       :target: ../../_images/play20-scalaide20-19.png

*   Add variables to ``models.Quote``, and make it a case class.

    .. code-block:: scala

       package models
       
       case class Quote(val text: String, val author: String) {
       
       }

*   Add an extra parameter to the ``index.scala.html`` view and update the layout.

    .. code-block:: scala

       @(message: String, quote: models.Quote)
       
       @main("Welcome to Play 2.0") {
       
           <p>@quote.text<em> - @quote.author</em></p>
       
       }

*   The templates are transformed into Scala code by the Play framework. As Play has been started in auto-reloading mode in the background, templates are recompiled as soon as the file is saved.

    After saving the file, the changes are picked up by Scala IDE, and it reports an error in the code of ``Application.scala``. The application is not using the template correctly.
    
    .. image:: images/play20-scalaide20-17.png
       :alt: compilation error
       :width: 100%
       :target: ../../_images/play20-scalaide20-17.png

*   Fix the application code, using a smart quote. And fix the imports as needed.

    .. code-block:: scala

         def index = Action {
           Ok(views.html.index("Your new application is ready.",
               Quote("Citer les pensees des autres, c'est regretter de ne pas les avoir trouvees soi-meme.",
                   "Sacha Guitry")))
         }

*   The code compiles. Check the result in the internal web browser.

    .. image:: images/play20-scalaide20-18.png
       :alt: done
       :width: 100%
       :target: ../../_images/play20-scalaide20-18.png

Going further
-------------

You now have all you need to create great web applications with Play 2.0 and Scala.

For more information about Play 2.0, check out the `embedded documentation`_.

For more information about Scala, go to the `documentation website`_ or get the downloadable `eBook`_.

Feedback
--------

This guide is managed through in the `Scala IDE documentation project`_ on github.
Please use github tickets and pull requests system for feedback.

Luc Bourlier - `+Luc Bourlier`_ `@sky1uc`_


.. _#1000907: http://www.assembla.com/spaces/scala-ide/tickets/1000907
.. _Scala IDE: http://www.scala-ide.org
.. _Scala IDE documentation project: https://github.com/scala-ide/docs
.. _Eclipse: http://www.eclipse.org/
.. _Play documentation: http://www.playframework.org/documentation/2.0/Installing
.. _sbteclipse: https://github.com/typesafehub/sbteclipse
.. _embedded documentation: http://localhost:9000/@documentation/Home
.. _documentation website: http://docs.scala-lang.org/
.. _eBook: http://typesafe.com/resources/scala-for-the-impatient
.. _+Luc Bourlier: https://plus.google.com/106787944777810934000/posts
.. _@sky1uc: https://twitter.com/sky1uc
