Typing and Viewing
==================

As You Type Error Reporting
---------------------------

The Scala code is check as it is entered. It allows to see small or more complex compilation error as the code is type. No more endless cycle *code → code → code → compile → error reported → fix error → compile → error reported → fix error →...*.

.. image:: ../images/feature-typing-error-01.png

Code Completion
---------------

Scala IDE provides full code completion support, returning the entities available in the current context. Code completion is enable by using ``Ctrl+Space`` in the Scala Editor.

In **Scala source files**, code completion returns the valid Scala and Java proposals.

.. image:: ../images/feature-code-completion-01.png

The Scala elements compatible with Java are provided in **Java source files**.

.. image:: ../images/feature-code-completion-02.png

Code Templates
--------------

Code templates are available in the completion proposals in code assist. They provide shortcuts for boilerplate code like main method definition and asInstanceOf invocation.

.. image:: ../images/feature-template-01.png

The list of available templates can be viewed and modified in the ``Scala → Templates`` preference page.

Mark Occurrences
----------------

As in the Java tooling, Mark Occurrences highlights within a file where a type, variable or method is referenced.

.. image:: ../images/feature-occurences-01.png

Quick Fix Imports
-----------------

`Quick Fix`_ can be used to easily add a required import statement.

.. image:: ../images/feature-quickfix-import-01.png

.. _typingviewing_show-inferred-semicolons:

Show Inferred Semicolons
------------------------

Use the |icon| toggle button in the to enable *infer semicolons*. It will display the inferred semicolons in gray as an overlay on the code.

.. |icon| image:: ../images/show_inferred_semicolons.png

Without: |without| With: |with|

.. |without| image:: ../images/feature-inferredsemicolons-01.png
.. |with| image:: ../images/feature-inferredsemicolons-02.png

Structured Selection
--------------------

Using ``alt+shift+up`` and ``alt+shift+down`` selects larger or smaller portion of the AST. It allows for quick selection of sub-expressions or statements.

.. image:: ../images/feature-structured-selection-01.png

Syntax Highlighting
-------------------

The Scala editor has full syntax highlighting support, including comments, control structures and embedded XML.

.. image:: ../images/feature-syntax-highlighting-01.png


.. _Quick Fix: http://wiki.eclipse.org/FAQ_What_is_a_Quick_Fix%3F
