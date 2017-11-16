.. include:: /global_defs.hrst

Auto Edits |new|
================

Auto Edits are nice little features that facilitate working with source code a lot. Normally, users don't notice them when they are working because they do their work while users type. This stands in contrast to code completion which normally requires user interaction for its activation and even more user interaction to choose one of the completions that should be applied. Auto Edits are so lightweight that users never need to think about them because they always work the way they should.

All available Auto Edits can be found in ``Preferences → Scala → Editor``. They can be set or unset on the fly which means that an open editor applies any changes in the preferences immediately.

Surround selection
------------------

This group of Auto Edits are applied when some text inside of the editor is selected and one of the keys that insert a ``[``, ``{``, ``(`` or a ``"`` is pressed. In that case the whole selection is surrounded in the way the user wants it.

**Example:** When the text ``Hello World`` is selected and the ``{`` key is pressed, the text ``{Hello World}`` is inserted into the editor.

Auto closing braces
-------------------

As the name tells, this Auto Edit automatically closes braces when one types an opening brace. Even better, this feature can decide if it is really necessary to close opening braces. The rules on when braces automatically are closed are deterministic and easy to understand:

 * The first rule is that braces are only closed when there doesn't already exist a closing brace
 * The second rule is that braces are not closed whenever the cursor is positioned directly before non white-space

Both rules are only applied if the option called

 * Enable auto closing braces when editing an existing line

is unset - if this option is set braces are always closed regardless if it is useful or not.

**Example:** In Scala users often decide to change parentheses to braces because the latter can contain multiple expressions. In the following line of code ::

  List(1, 2, 3) map (x => x+1)

one can insert an opening brace directly before the opening parenthesis::

  List(1, 2, 3) map {(x => x+1)

In this case no closing brace in inserted. Instead the editor waits until the users has done all changes and presses enter. After that, the output may look something like this::

  List(1, 2, 3) map {
    (x => x+1)
  }

where the closing brace and the code between the braces is inserted at the correct position as one expects it.

Auto closing literals
---------------------

Whenever one enters character or string literals the corresponding closing part of the literal is automatically inserted. If one enters three times quotation marks a multi line string literal is created. The editor also supports the auto deletion of such literals whenever they are empty and one deletes the opening part of the literal.

At the moment this feature cannot be disabled, it is always active.

Auto indentations
-----------------

This is definitely one of the most useful features an editor can have. It is another Auto Edit nobody notices its existence but everybody immediately notices its absence when it does not work the way it should. The possibility to automatically indent the cursor to the right position saves a lot of time while coding. Thus, it is obvious that a lot of effort is spent in this feature to maximize usability of the IDE.

**Example:** If one hits enter at the caret position (``^``) in this code snippet ::

  def f(i: Int) = {^}

one will get this as result::

  def f(i: Int) = {
    ^
  }

Auto indentation also works inside multi line comments and Scaladoc::

  /**
   *         indented text^
   */

If one hits enter at the caret position this is the result::

  /**
   *         indented text
   *         ^
   */

This feature is always active and can't be disabled.

Smart semicolons
----------------

Smart semicolons allow to place an inserted semicolon at the end of a line instead of the position where the cursor is placed. This is useful to jump over closing parenthesis with one keystroke. For example if one enters the semicolon at the caret position (``^``) in this example ::

  a(b(c(d(e^))))

this is the result::

  a(b(c(d(e))));^

If it is intended to place a semicolon in the middle of a line one can hit backspace to move the semicolon back to its original position.

This feature is inherited from the Java editor, thus at the moment it can only be configured under ``Java → Editor → Typing → Automatically insert at correct position``.

Automatic escapes
-----------------

In string or character literals it can be useful to automatically escape all the signs that can lead to compiler errors if they are inserted. Inside of string literals this could be the insertion of a quotation mark because the compiler would accidentally treat it as the closing part of the literal. To avoid such compiler errors there are two options, which - when activated - prefix such signs with a backslash to escape them. The two options are

 * Automatically escape " signs in string literals
 * Automatically escape \\ signs in string and character literals

Furthermore, there is an option to automatically remove such escaped signs with one keystroke if either the backslash or the corresponding sign is deleted.

Automatic expanding multi line comments and Scaladoc
----------------------------------------------------

There also exists an option that automatically closes multi line comments and Scaladoc if one hits enter, it is called

 * Automatically close multi line comments and Scaladoc

If a multi line or Scaladoc comment is already closed they are expanded automatically if one inserts a new line. This means that each new line is automatically indented to the position of the first sign of the previous line. If the previous line starts with a star, then this star is included too, otherwise not.

**Example:** The previous line starts with a star::

  /*
   * a multi line comment^
   */

If one hits enter at the caret position (``^``) the comment is expanded to::

  /*
   * a multi line comment
   * ^
   */

On the other side if the previous line doesn't start with a star ::

  /*
     multi line comment^
   */

the result is the following::

  /*
     multi line comment
     ^
   */