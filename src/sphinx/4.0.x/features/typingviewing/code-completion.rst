.. include:: /global_defs.hrst

Code Completion |updated|
=========================

Scala IDE provides full code completion support, returning the entities available in the current context. Code completion is enable by using ``Ctrl+Space`` in the Scala Editor.

In **Scala source files**, code completion returns the valid Scala and Java proposals.

.. image:: images/feature-code-completion-01.png
   :width: 100%
   :target: ../_images/feature-code-completion-01.png

The Scala elements compatible with Java are provided in **Java source files**.

.. image:: images/feature-code-completion-02.png
   :width: 100%
   :target: ../_images/feature-code-completion-02.png

Type completion relevance |new|
-------------------------------

The order in which type completions are presented to the user can be tweaked in ``Preferences → Scala → Editor → Completions`` based on their package:

.. image:: images/feature-code-completion-03.png
   :width: 100%
   :target: ../_images/feature-code-completion-03.png

Generally, completions are ordered by a so called relevance score, that is modified accordingly if the package matches one of the supplied regular expressions.
**Favorite Packages** get a significant, and **Preferred Packages** a slight bonus to their relevance scores. Likewise, **Unpopular Packages** get a slight, and
**Shunned Packages** a significant penalty. If a package is matched by multiple regular expressions, the associated bonuses or penalties are simply added up (even
if the regular expressions are part of the same group). This can be exploited for fine tuning. For example, adding ``scala\.(?!xml).*`` to **Favorite Packages**
and ``scala\.collection\.immutable\b.*`` to **Preferred Packages** will add a significant bonus to all Scala types (except for APIs under ``scala.xml``), but an 
even slightly larger bonus to immutable Scala collections, to make sure that they are preferred to their mutable counterparts. Note that we would have gotten a
similar effect, if we had added ``scala\.collection\.immutable\b.*`` to **Favorite Packages** as well, but then the added bonus for immutable collections would have 
been bigger. Related, but not identical behaviour, would be observed after adding ``scala\.collection\.mutable\b.*`` to **Unpopular Packages** or **Shunned Packages**.

.. warning:: The supplied regular expressions are matched against full package names. A partial match is not enough. Always double check them if you wonder why your changes do not have the desired effect.

Completion overwrites
---------------------

Users can choose if identifiers inserted by the code completion mechanism should overwrite already existing ones. This is a very useful feature if one decides to change already existing source code. For example, if we have the following ::

  List(1).fl^map(List(_))

where ``^`` denotes the cursors position, it would not be that useful when as completion ``flatMap`` is chosen but the already existing ``map`` is not replaced. Completion overwrites allow exactly these sort of replacements. They need to be configured in ``Preferences → Java → Editor → Content Assist`` and then in the section ``Insertion``.

A small detail to mention here, is, that it is possible to toggle between the completion modes by holding ``Ctrl`` while applying the completion.
