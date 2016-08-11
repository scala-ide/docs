How to create a Scala IDE release
=================================

This document describes how to build a Scala IDE release and the corresponding Eclipse products.

Prerequisites:

* Access to Amazon S3, which hosts the Scala IDE products and the releases
* Push permissions to all Github repos that need to be included in the release
* Access rights to the keystore that contains the keys for the JAR/Eclipse signing
* A GPG key if you want to sign any Git tags

If you want to build a new Scala IDE release but you don't have access to all of the things, please ask one of the current IDE maintainers for them. Also, make sure to read all steps first before you run the first steps. You don't want to accidentally release something that should not have been released yet.

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

#. Before uber-build can be run, it may be necessary to specify some environment variables. All the variables are part of the file ``uber-build/uber-build-config.sh``.

#. Run uber-build:

   .. code-block:: bash

     cd /path/to/uber-build
     ./uber-build config/config-file.conf

   If everything completes successfully, uber-build continues with uploading all binaries of the repos that were built.

#. The binaries are uploaded to a staging area. This means that they are not yet available to users once the uploading has been finished. As long as the binaries are in the staging area, they can be safely tested and verified. If something is wrong, repeat the previous steps until the uploaded binaries are ready to be published to users. Once this is the case, simply run the generated file ``target/release-from-staging-area.sh``. Note, that after this step there is no return! The release is online and can be seen by everyone.

#. uber-build doesn't automatically upload the Scala IDE products to the Amazon S3 host, therefore it has to be done manually. Get a client from somewhere that allows you to access the S3 contents and enter the credentials you got from the current IDE maintainers. For every new product a directory should be created in the directory ``downloads.typesafe.com/scalaide-pack``. Note, that uber-build already downloaded a CLI to access S3, it can be run with ``source target/aws-virtualenv/bin/activate`` and left by typing ``deactivate``. This interface is cumbersome to use manually, therefore you may want to use a graphical interface.

   The products can be found in the local directory ``target/product/org.scala-ide.product/target/products/``.

#. Build a changelog for the ``scala-ide/docs`` repo. Run the following script in the ``scala-ide/scala-ide`` repo to generate the changelog:

   .. code-block:: bash

     cd /path/to/scala-ide-repo
     ./GenChangeLog.bash <previous-release> <new-release>

   where ``previous-release`` and ``new-release`` are tags. The output of this script should be included in ``scala-ide/docs/src/sphinx/changelog.rst``. Note, that the output of this script is not perfect, you may want to manually improve it.

#. Create release notes for the ``scala-ide/scala-ide.github.com`` repo. See the README of this repo for more information about how to proceed in creating the release notes.

#. That's it you are done.
