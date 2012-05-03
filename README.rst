:orphan:

Documentation for Scala IDE
---------------------------

This documentation web site is using `Sphinx`_ (version 1.1.2) to generate HTML pages from `reStructuredText`_ files.

To setup Sphinx locally, use ``easy_install``:

::

    $ sudo easy_install "sphinx==1.1.2"
    $ sudo easy_install pygments # required on some systems

Then simply use ``make clean html`` from the repository root to generate the files.

.. _reStructuredText: http://docutils.sourceforge.net/rst.html
.. _Sphinx: http://sphinx.pocoo.org/

ValueError: unknown locale: UTF-8
.................................

On Mac osX, it appears to have some 'local' problem. Running the following fixes it:

::

    export LC_ALL=en_US.UTF-8
    export LANG=en_US.UTF-8
