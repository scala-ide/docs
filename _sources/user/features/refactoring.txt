Refactoring
===========

`Scala Refactoring`_ is integrated in Scala IDE. It offers a broad range of refactoring actions.

Extracting Local Values
-----------------------

The `Extract Local`_ action does the opposite work of Inline Local. It creates a value of an expression. It is a must use to make code more readable.

Select an expression to extract.

.. image:: ../images/feature-refactoring-extract-local-01.png

Call the action, then set a name for the created value.

.. image:: ../images/feature-refactoring-extract-local-02.png

Extracting Methods
------------------

The `Extract Method`_ action extract the set of selected statements into a new method. The original code is replaced by the equivalent method call.

.. image:: ../images/feature-refactoring-extract-method-01.png

Inlining Local Values
------------------------

The `Inline Local`_ action helps removing unneeded values.

The action is performed on the currently selected value.

.. image:: ../images/feature-refactoring-inline-local-01.png

Organizing Imports
------------------

The `Organize Imports`_ action allows to do some automated cleanup on the list of imports. It removes the unused or unneeded imports and organizes the remainder.

.. image:: ../images/feature-refactoring-organize-import-01.png

This action can be configure in the ``Scala → Organize Import`` preference page.

Renaming
--------

The `Rename`_ action allows to change any name, and update its reference.

The action is performed in in-line mode if the name as only a local scope.

.. image:: ../images/feature-refactoring-rename-01.png

The action is done through a wizard if the name can be referenced from different source files.

.. image:: ../images/feature-refactoring-rename-02.png

.. _Extract Local: http://scala-refactoring.org/extract-local/
.. _Extract Method: http://scala-refactoring.org/extract-method/
.. _Inline Local: http://scala-refactoring.org/inline-local/
.. _Organize Imports: http://scala-refactoring.org/organize-imports/
.. _Rename: http://scala-refactoring.org/rename/
.. _Scala Refactoring: http://scala-refactoring.org/

