Documentation update from Hydrogen
==================================

This lists updates to the current which have to be merged at release time

.. warning::

   **Images! Links!**

   When the elements in this page are merged in the main doc, double check the path of the images and relative links, as they may be different.



**(from user/features/typing and viewing)**

Mark Occurrences
----------------

As in the Java tooling, Mark Occurrences highlights within a file where a type, variable or method is referenced.

.. image:: ../../user/images/feature-occurences-01.png

This feature is disabled by default, as it can have an impact on the editor performance, but it is really useful when exploring code. 

It can be enabled in the ``Scala -> Editor`` preference page, or using the button in the main toolbar.

.. image:: ../images/mark-occurrences-toolbar.png

**(from user/faq, to be added after 'no Scala library')** 

Scala IDE complains about '... is cross-compiled with an incompatible version of Scala ...'
...........................................................................................

In Scala IDE 3.0.0, the classpath validator has been extended to check the additional libraries. It checks in the name of the jar, which version of Scala it has been compiled against. If this version doesn't match the version of Scala used by the project, a problem is reported.

If this check returns a false-negative, it can be disabled at the workspace level, or at the project level. The setting is ``withVersionClasspathValidator`` in the ``Scala → Compiler → Build Manager`` preference section.

