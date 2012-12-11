:orphan:

Documentation for Scala IDE
---------------------------

This documentation web site is using `Sphinx`_ (version 1.1.2) to generate HTML pages from `reStructuredText`_ files.

To setup Sphinx locally, use ``easy_install``:

::

    $ sudo easy_install "sphinx==1.1.2"
    $ sudo easy_install pygments # required on some systems

To generate the files, use the ``make-site`` task in sbt:

::

    $ sbt make-site

To deploy the changes, use the ``ghpages-push-site`` task:

::

    $ sbt make-site ghpages-push-site

.. _reStructuredText: http://docutils.sourceforge.net/rst.html
.. _Sphinx: http://sphinx.pocoo.org/

ValueError: unknown locale: UTF-8
.................................

On Mac osX, it appears to have some 'local' problem. Running the following fixes it:

::

    export LC_ALL=en_US.UTF-8
    export LANG=en_US.UTF-8
