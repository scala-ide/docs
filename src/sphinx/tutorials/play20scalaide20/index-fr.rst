:orphan:

Configurer et utiliser Play framework 2.0 avec Scala IDE 2.0
============================================================

|usflag| :doc:`english version<index>`

.. |usflag| image:: ../../user/images/usflag.png

Que contient ce guide?
----------------------

Ce guide vous montera comment configurer une application web Play pour pouvoir l'importer dans Scala IDE, comment configurer `Scala IDE`_ pour pouvoir utiliser correctement le Play framework et finalement comment développer un application web Play avec Scala IDE.

*La version anglaise est la version de référence. J'essaierai de garder à jour la version française.*

Prérequis
.........

*   `Eclipse`_ 3.6.2 (Helios) avec Scala IDE pour Scala 2.9 installé (update site: http://download.scala-ide.org/releases-29/stable/site).

    La page :ref:`getting started<gettingstarted_getting-started>` (en anglais) contient les instructions pour l'installation de Scala IDE.

*   Une connaissance de base de l'interface utilisateur d'Eclipse est nécessaire.

*   Aucune connaissance du langage Scala est nécessaire (dans ce guide).

*   Aucune connaissance de Play framework est nécessaire (dans ce guide).

Installer Play 2.0
------------------

Pour pouvoir créer une web application Play, Play framework est nécessaire. Si vous ne l'avez pas encore installé, suivez ces quelques étapes, ou utilisez la `documentation de Play`__.

*   Téléchargez Play framework 2.0 sur http://www.playframework.org/2.0.

*   Un-zippez l'archive dans votre répertoire préféré. Nous utiliserons ``/path/to/play20`` dans ce document.

*   Pour simplifier l'utilisation, ajoutez le répertoire de Play dans le PATH:

    .. code-block:: bash

       export PATH=$PATH:/path/to/play20

__ `Play documentation`_

Créer une application Play 2.0
------------------------------

*   Dans votre répertoire de développement, demandez à Play de créer une nouvelle application web, de type ``simple Scala application``.

    .. image:: images/play20-scalaide20-01.png
       :alt: play new testApp
       :width: 100%
       :target: ../../_images/play20-scalaide20-01.png

*   Allez dans le répertoire de l'application.

    .. image:: images/play20-scalaide20-02.png
       :alt: cd testApp
       :width: 100%
       :target: ../../_images/play20-scalaide20-02.png

*   Et lancez Play.

    .. image:: images/play20-scalaide20-03.png
       :alt: play
       :width: 100%
       :target: ../../_images/play20-scalaide20-03.png

*   Dans Play, démarrez l'application web que vous venez de créer.

    .. image:: images/play20-scalaide20-04.png
       :alt: run
       :width: 100%
       :target: ../../_images/play20-scalaide20-04.png

*   Vérifiez que l'application fonctionne: http://localhost:9000/.

    .. image:: images/play20-scalaide20-05.png
       :alt: running
       :width: 100%
       :target: ../../_images/play20-scalaide20-05.png

Configurer l'application web Play 2.0 pour Scala IDE
----------------------------------------------------

Maintenant que l'application Play fonctionne, il faut la configurer pour pouvoir l'importer dans Scala IDE.

Play 2.0-RC1 intègre `sbteclipse`_, qui permet de créer les fichiers de configuration d'un projet pour Eclipse.

*   D'abord, sortez du mode 'run' de Play, en utilisant ``ctrl-d``.

    .. image:: images/play20-scalaide20-06.png
       :alt: ctrl-d, exit
       :width: 100%
       :target: ../../_images/play20-scalaide20-06.png

*   ``eclipsify`` est la commande à utiliser pour lancer sbteclipse depuis Play.

    .. image:: images/play20-scalaide20-09.png
       :alt: eclipse
       :width: 100%
       :target: ../../_images/play20-scalaide20-09.png

*   Relancez l'application web, en mode 'auto-reloading', en utilisant ``~ run``, de manière à ce qu'elle fonctionne en arrière plan.

    .. image:: images/play20-scalaide20-10.png
       :alt: run
       :width: 100%
       :target: ../../_images/play20-scalaide20-10.png

Configurer Scala IDE pour l'application web Play 2.0
----------------------------------------------------

Changer quelques préférences dans Eclipse va permettre de rendre son utilisation plus simple.

*   Ouvrez la *internal web browser view* dans Eclipse, et vérifiez que vous pouvez accéder votre application web.

    .. image:: images/play20-scalaide20-12.png
       :alt: http://localhost:9000/
       :width: 100%
       :target: ../../_images/play20-scalaide20-12.png

*   Configurez Eclipse pour que les changements dans le système de fichier soient charger automatiquement.

    .. image:: images/play20-scalaide20-13.png
       :alt: refresh automatically
       :width: 100%
       :target: ../../_images/play20-scalaide20-13.png

*   Si vous n'avez pas la version *Web Development Tools* d'Eclipse installée, Eclipse ouvre les fichiers ``.html`` dans un navigateur. Configurer Eclipse pour qu'il utilise le ``Text Editor`` à la place.

    .. image:: images/play20-scalaide20-14.png
       :alt: HTML file in text editor
       :width: 100%
       :target: ../../_images/play20-scalaide20-14.png

Importer l'application web Play dans Scala IDE
----------------------------------------------

Tout est configuré, il est temps d'importer le projet dans Scala IDE.

*   Importez l'application Play 2.0 en tant que ``Existing Projects into Workspace``.

    .. image:: images/play20-scalaide20-15.png
       :alt: import project
       :width: 100%
       :target: ../../_images/play20-scalaide20-15.png

*   La configuration générée par sbteclipse permet l'utilisation du project dans Eclipse, mais elle n'est pas optimale avec Scala IDE.

    Ajoutez le répertoire ``target/src_managed/main`` comme répertoire source.

    .. image:: images/play20-scalaide20-20.png
       :alt: add the managed sources folder
       :width: 100%
       :target: ../../_images/play20-scalaide20-20.png

*   Et enlevez le répertoire ``classes_managed`` du build path.

    .. image:: images/play20-scalaide20-21.png
       :alt: remove managed classes folder
       :width: 100%
       :target: ../../_images/play20-scalaide20-21.png

*   Tout est parfait, tout compile.

    .. image:: images/play20-scalaide20-16.png
       :alt: everything compiles
       :width: 100%
       :target: ../../_images/play20-scalaide20-16.png

Faire un peu de développement
-----------------------------

Maintenant que tout est configuré, le vrai développement peut commencer.

Modifions la page principale pour afficher une citation à la place de la page de défaut.

*   D'abord, créez la classe ``models.Quote`` en utilisant l'assistant nouvelle ``Scala Class``.

    .. image:: images/play20-scalaide20-19.png
       :alt: create model.Quote
       :width: 100%
       :target: ../../_images/play20-scalaide20-19.png

*   Ajoutez les variables à ``models.Quote``, et faites en une case classe.

    .. code-block:: scala

       package models
       
       case class Quote(val text: String, val author: String) {
       
       }

*   Ajoutez un paramètre supplémentaire à la vue ``index.scala.html`` et modifiez la mise en page.

    .. code-block:: scala

       @(message: String, quote: models.Quote)
       
       @main("Welcome to Play 2.0") {
           
           <p>@quote.text<em> - @quote.author</em></p>
       
       }

*   Les templates sont transformés en code Scala par le Play framework. Comme Play a été lancé en mode 'auto-reloading' en arrière plan, les templates sont recompilés dès que le fichier est sauvegardé.

    Apres avoir sauvegardé le fichier, les changements sont chargés par Scala IDE, et une erreur est reporté dans le code de ``Application.scala``. L'application n'utilise pas le template correctement.
    
    .. image:: images/play20-scalaide20-17.png
       :alt: compilation error
       :width: 100%
       :target: ../../_images/play20-scalaide20-17.png

*   Corrigez le code de l'application, en utilisant une citation élégante. Et corrigez les imports au besoin.

    .. code-block:: scala

         def index = Action {
           Ok(views.html.index("Your new application is ready.",
               Quote("Citer les pensees des autres, c'est regretter de ne pas les avoir trouvees soi-meme.",
                   "Sacha Guitry")))
         }

*   Le code compile. Vérifiez le résultat dans le navigateur interne.

    .. image:: images/play20-scalaide20-18.png
       :alt: done
       :width: 100%
       :target: ../../_images/play20-scalaide20-18.png

Aller plus loin
----------------

Vous avez maintenant tout ce dont vous besoin pour créer de grandes applications web avec Play 2.0 et Scala.

Pour plus d'information sur Play 2.0, utilisez la `documentation intégrée`__.

__ `embedded documentation`_

Pour plus d'information sur Scala, allez sur le `site de documentation`__ ou récupérez le `eBook`_ téléchargeable.

__ `documentation website`_

Feedback
--------

Ce guide fait parti du `project de documentation de Scala IDE`__ sur github.
Vous pouvez utiliser le système de ticket et de pull request de github pour nous faire parvenir des commentaires ou autre.

__ `Scala IDE documentation project`__

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
