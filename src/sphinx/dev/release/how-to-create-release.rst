How to create a Scala IDE release
=================================

This document describes how to build a Scala IDE release and the corresponding Eclipse products.

Prerequisites:

* Your public SSH key needs to be added to Scala IDE's file hoster
* You also need access to Amazon S2, which hosts the Scala IDE products
* Push permissions to all Github repos that need to be included in the release
* Access rights to the keystore that contains the keys for the JAR/Eclipse signing
* A GPG key if you want to sign any Git tags
* Access to Jenkins if you want to build the Scala IDE ecosystem by yourself

If you want to build a new Scala IDE release but you don't have access to all of the things, please ask one of the current IDE maintainers for them.

#. The ``scala-ide/scala-ide-product`` repo needs to be updated to the newest version number. This can be done with the following command:

   .. code-block:: bash

     cd /path/to/scala-ide-product
     mvn -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=<new-version>-SNAPSHOT

   where ``<new-version>`` needs to be replaced by the actual version, which is for example ``4.0.0``. Note, it is not relevant to add RC or final version appendixes here. This command forgets one file that needs to be updated too: ``org.scala-ide.sdk.feature/templates/feature.xml``.

#. Create a new config file in the ``scala-ide/uber-build`` repo. The config files are located in the ``uber-build/config`` directory. The easiest way to do this is to copy the config file of the previous release. The config file contains a ``*_GIT_BRANCH`` property, which specifies the branch or tag of the corresponding git repo. During the build this branch or tag is checked out and then considered for the Scala IDE release. If the repo has been updated since the last release and the plan is to include these updates in the next release, a new tag needs to be specified.

#. After all tags are specified in the configuration file, they need to be set to all git repos. This can either be done manually or with the help of the ``uber-build/mk-git-tags.sh`` file. This file should be called in the following way:

   .. code-block:: bash

     cd /path/to/uber-build
     ./mk-git-tags.sh config/release-config-file.conf /path/to/scala-ide/base/dir "tag sign message"

   ``mk-git-tags.sh`` assumes that all repos under the ``scala-ide`` organization are located in a base directory. If this is the case it will load the specified config file and add all the tags to the repositories. After that it will upload them to Github. During this process all tags are signed with a GPG key. If such a key does not exist, either create it or create the tags manually without any signing.

#. In order to build the Scala IDE SDK (the Eclipse product), an Eclipse installation is required. Download the "Eclipse IDE for Java Developers" from their `download page <https://www.eclipse.org/downloads/>`_ and extract it somewhere in your file system. It is not important which OS dependent archive is downloaded - Eclipse will figure out by itself how to create executables for each OS based on the extracted Eclipse installation.

#. Before uber-build can be run, it is probably necessary to specify some environment variables. All the variables that may be necessary are part of the file ``uber-build/uber-build-exports.sh``.

#. Run uber-build:

   .. code-block:: bash

     cd /path/to/uber-build
     ./uber-build config/config-file.conf

   If everything completes successfully, uber-build continues with uploading all binaries of the repos that were built. Notice that you will be asked for the password of your SSH key before the uploading can be started.

   After the upload has been completed, the base ecosystem can be found in directory ``~/scala-ide.dreamhosters.com/sdk/next/lithium/e44/scala211/stable/`` whereas all the plugins can be found in ``~/scala-ide.dreamhosters.com/plugins/$pluginName/releases/e44/2.11.x/``.

   To login use ``ssh scalaide@proxima-centauri.dreamhost.com``.


#. uber-build doesn't automatically upload the Scala IDE products to Amazon S3 host, therefore it has to be done manually. Get a client from somewhere that allows you to access the S3 contents and enter the credentials you got from the current IDE maintainers. For every new product a directory should be created in the directory ``downloads.typesafe.com/scalaide-pack``.

   The products can be found in the local directory ``target/product/org.scala-ide.product/target/products/``.

#. Build a changelog for the ``scala-ide/docs`` repo. Run the following script in the ``scala-ide/scala-ide`` repo to generate the changelog:

   .. code-block:: bash

     cd /path/to/scala-ide-repo
     ./GenChangeLog.bash <previous-release> <new-release>

   where ``previous-release`` and ``new-release`` are tags. The output of this script should be included in ``scala-ide/docs/src/sphinx/changelog.rst``. Note, that the output of this script is not perfect, you may want to manually improve it.

#. Create release notes for the ``scala-ide/scala-ide.github.com`` repo. See the README of this repo for more information about how to proceed in creating the release notes.

#. A Scala IDE ecosystem build needs to be triggered in Jenkins. This can be done manually but Jenkins builds the ecosystem periodically and completely automatically. The ecosystem is created based on the binaries you uploaded with uber-build. After this happened, the ecosystem is located in a staging area on the server where you can check a last time that everything has been done correctly.

   The job in Jenkins that builds the ecosystem is called ``ecosystem/ecosystem-all`` and the staging area is located at ``http://download.scala-ide.org/sdk/next/lithium/e44/scala211/stable/``.

#. The last step is to release the IDE from the staging area to the big wide world. This needs to be done manually with the Jenkins job ``ecosystem/ecosystem-merge-bases``. After this job is run, the job ``ecosystem/ecosystem-all`` needs to run once again. This time it frees the ecosystem from the staging area. After both jobs are run, the ecosystem can be found in the directory ``http://download.scala-ide.org/sdk/lithium/e44/scala211/stable/``.
