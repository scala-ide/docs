Changelog
=========

4.0.0 - codename Lithium
------------------------

RC1 (unreleased)
.....................

 - Remove last calls to `JavaUI.getDocumentProvider`
 - Update to next version of build-tools
 - Add missing Export-Package declarations.
 - Fix thread check failure in reporting string of Implicit Highlighter (:ticket:`1002295`)
 - Don’t call `openWhenClosed` if the compilation unit is consistent.
 - Narrow the return types to API types in tests.
 - Save actions (:ticket:`1000900`, :ticket:`1000887`, :ticket:`1001138`, :ticket:`1002232`, :ticket:`1002240`, :ticket:`1002239`, :ticket:`1002234`, :ticket:`1002225`, :ticket:`1002227`) |new|
 - Redesign of quick assists (:ticket:`1002286`, :ticket:`1002283`) |new|
 - Fix regression in syntax color preference page (:ticket:`1002289`)
 - Better use of compiler APIs.
 - Correct selection after multi edit
 - Add the proper dependency to Scala 2.11 (sources) form SDT (:ticket:`1002290`)
 - New ScalaReconciler implementation that listens to activation events, like the Java one (:ticket:`1002215`)
 - Export API for Scala completions
 - Cleanup hyperlink code and APIify
 - Ignore interrupts while waiting for presentation compiler.
 - Scala Hover API and fixes
 - Clear doc comments when fishing reloads.
 - Show simple names instead of fully qualified names in quick fixes |new|
 - Harden locate symbol. Sometimes there is no source provider.
 - An API for Util methods (:ticket:`1002247`)
 - Fix deadlock by moving `res.get` outside of synchronized block. (:ticket:`1002275`)
 - Remove aspect hover
 - Refactor the way compilation units work
 - Open API for syntax coloring of Scala files
 - Fix ScalaWordFinder AIOOBE (:ticket:`1002238`)
 - Control persistence of PC doc comments.
 - Basic ScalaDoc prettyfications  |new|
 - Changes rich type method names to avoid conflicts
 - Scaladoc display on completion & showToolTip (:ticket:`1000453`, :ticket:`1000210`) |new|
 - Additionnal changes needed to remove sdt.editor from the play plugin
 - Adds a test which checks sbt reorders libs on classpath
 - Scala Installation Support Improvements
 - Prevent Race Condition in mkImplicitConversionAnnotation (:ticket:`1002269`)
 - Disentangle presentation compiler and Scala project
 - Adds missing getOption() synchronization call
 - Close ScalaPresentationCompilers which weren't used for specified time |new|
 - Initial stab at a cleaner interface for Scala presentation compiler.
 - Addditionnal changes for ScalaImage API
 - Fixes problem build disabled after clean
 - Completion improvements for higher-order-functions (:ticket:`1002250`) |new|
 - Revert "Unused member warning doesn't highlight whole definition anymore... (:ticket:`1002209`)
 - Set ScalaImages constants as API
 - Temporary disables UI support for -Xlint
 - A couple of simple improvements in completions (:ticket:`1002171`)
 - Fixes a few issues linked to ClasspathTests
 - Update entries of moved logging packages in Manifest files
 - Moves non-API logging classes in internal packages
 - Tab to space conversion
 - Fixes switching between Scala Installations
 - Fixed NPE on first start on Luna (:ticket:`1002242`)
 - Remove `EclipseUserSimulator`
 - Switches to package import for the dependency to osgi.service.location
 - Moves content of core.api in core, and adds Ixxx prefix
 - Faster implementation of `ScalaCommentScanner` (:ticket:`1002241`)
 - Shut up noisy logger. (:ticket:`1002228`)
 - Add *.tmpBin files to .gitignore
 - Mark some completion tests as flaky.
 - Creates an API for ScalaPlugin
 - Fix Luna build by using the AJDT dev version of the weaving hook.
 - Unify kepler and luna branches as different profiles under the same build (:ticket:`1002211`)
 - T1002123 spell checker (:ticket:`1002123`)
 - Shorten log message in SPCP regarding restarted compilation units
 - Interrupt Scala builder on resource change (:ticket:`1002229`)
 - Updates to scala 2.11.3-SNAPSHOT
 - Hover improvements2 (:ticket:`1001567` :ticket:`1001534`, :ticket:`1001897`, :ticket:`1001667`)
 - Fix NPE in `AllMethodsTraverserImpl` (:ticket:`1002217`)
 - T1002191 enable new wizard elements (:ticket:`1002191`)
 - Sbt builder fix and refactor (:ticket:`1002148`)
 - Treat projects in subfolders correctly in `NewFileWizard` (:ticket:`1002188` :ticket:`1002185`)
 - Use `Import-Package` instead of `Require-Bundle` for org.eclipse.core.runtime
 - Revert "Merge pull request #742 from sschaef/remove-java-partitions"
 - Remove java partitions
 - Update build-all script to kepler.
 - Use UTF-8 for tests.
 - An API for ScalaProject
 - adds space instead of comma in multi-ticket display
 - A more sane error message about what profile is missing.
 - Fix semicolon painter (:ticket:`1002170`, :ticket:`1002153`)
 - Reformat error messages from presentation compiler
 - Some cleanup & 2.10 deprecation upheaval

M3 (2014-07-25)
.....................

 - Makes sdt depend on kepler jdt
 - Sets the right name for the test source jar
 - New File Wizard (:ticket:`1002103`) |new|
 - Scala Installations support stage II : choose your own installation |new|
 - Redesign of the bug report dialog
 - Adds support for Scala 2.12
 - Don’t reuse platform classloader when ScalaInstallation matches its version
 - Disable tab to spaces converter in TextViewer (:ticket:`1002167`)
 - Don't use Scala reflection for asInstanceOfOpt (:ticket:`1002128`)
 - Show variable values in hovers when in suspended debug mode |new|
 - Scala Installations support (:ticket:`1001777`) |new|
 - Finish cleanup of removed extraction refactorings
 - Restore Run Selection In Interpreter functionality (:ticket:`1002164`)
 - Integrate new extraction refactorings
 - Highlight dynamic calls with type parameters (:ticket:`1002162`)
 - Revert wrong optimization in 1b83945. Always reset compilers on classpath change
 - Cleanup/custom code removal
 - Prevent NoSuchElementException in Extract trait. (:ticket:`1001967`)
 - T1002151 wrong cursor position after completion (:ticket:`1002151`)
 - Fix race condition due to call of `Tree.symbol` (:ticket:`1002132`)
 - Pull loop invariant code outside of the loop (performance improvement in code completion)
 - Fixed auto-breaking comments (:ticket:`1002116`, :ticket:`1002119`, :ticket:`1002120`, :ticket:`1002115`)
 - New test suite for code completion / text edits (:ticket:`1001912`)


M2 (2014-05-27)
.....................

- Support for name hashing in the incremental builder (:ticket:`1002117`)
- Build support for several versions of Scala
- Added the ability to compile the sbt compiler-interface on the fly
- Increase test timeouts (possible source of flaky classpth tests).
- Better support for auto-completion of identifiers that require backticks  (:ticket:`1001371`)
- Auto generate stubs for abstract members (:ticket:`1000224`, :ticket:`1000538`) |new|
- auto breaking comments (:ticket:`1002101`)
- Add `override` keyword to some methods (:ticket:`1001938`, :ticket:`1001937`, :ticket:`1001936`)
- Fixes default for project specific compiler settings (:ticket:`1002083`)
- Use Sbt transactional classfile writer (:ticket:`1001953`)
- Disable MigrationPreferenceInitializer in UI less environment
- Simplifies EclipseSbtBuildManager (:ticket:`1002070`)
- Xsource internal support & ScalaVersion upheaval
- Updated aspectj dependency to latest stable (1.7.4).
- Cleanup templates
- New refactoring menu (:ticket:`1002049`)
- Re-enable and improved Scala Templates (indentation) (:ticket:`1000058`)
- Replaces PropertyStore with a set of sparser platform calls.
- new syntax coloring for Dynamic calls (:ticket:`1001656`)
- Don't clean workspace unless compiler settings really changed (:ticket:`1002075`)
- Remove preference store listener on deactivation
- Disable javaextdirs setting in Scala projects. (:ticket:`1002072`)
- More robust handling of virtual files. (:ticket:`1002073`)
- Custom semantic highlighting extensions (:ticket:`1001989`)
- Small improvements in indentation (if-else, literal in val/var) (:ticket:`1001306`, :ticket:`1000415`)
- Enhance auto indent logic (:ticket:`1002037`, :ticket:`1001099`)
- Touches up the feature.xml descriptions of the plugins. (:ticket:`1001605`)
- Fix character encoding issue in about.ini
- Make Organize Imports NOT save automatically after edit. (:ticket:`1001573`)
- Add handler+icon for indent guide feature
- Reorganize preference pages
- Removes auto-generated feature.xml files from repo.
- Combines toolbar menu structures (:ticket:`1002042`)
- Make bump-version a simple(r) operation
- Fix compilation error shown with Xlint
- Rehaul of the package structure (using internal packages)
- Presentation Compiler should not reload managed units while keeping the lock (:ticket:`1002003`, :ticket:`1002007`, :ticket:`1001943`, :ticket:`1001911`)
- Show confirmation dialog before doing a workspace wide clean (:ticket:`1002043`)
- Implementation of an indent guide + test suite (:ticket:`1000828`, :ticket:`1002033`) |new|
- Add 'Xlint' and 'feature' to the compiler preference site (:ticket:`1002039`)
- Allow task tags to start with a special sign (:ticket:`1000991`)
- Refactorings in wizard components
- prevent NPE on classpath problems
- Reinstate call to JDT reconciler for side-effects (:ticket:`1002016`)
- Only ask for diagnostics at first install and if heap is not sufficient. (:ticket:`1001113`)
- Updating the License to the latest Scala License.
- Fix warning about adapted argument list
- Fix New Class Wizard JavaModelExceptions (:ticket:`1002006`)
- Removed FIXME in `ScalaSourceFile` (Refactoring)
- SI-8151 Prepare for removal of -Yself-in-annots
- Do not use system properties to configure incremental compiler.
- Using the enclosed type to fix completion and imports for inner classes. (:ticket:`1002002`)
- Fixed NPE on quick assist proposals (:ticket:`1002014`)
- Add an option to show API diffs when building (sbt incremental compiler debugging) (:ticket:`1001952`)
- Issue/remove continuations plugin support (:ticket:`1002012`, :ticket:`1002011`)
- Adds decorator extension point to change icons for Scala files (:ticket:`1001975`)
- Add synchronisation around map `getOrElse`.
- Don't add local suffix (one space character) for field completions (:ticket:`1001973`)
- Cache info about jar files that might contain Scala classifies. (:ticket:`1001999`)
- Workaround for race condition during source indexing
- Ignored completion test causing spurious failures on 2.11 build
- Correct completion overwrite behavior when nothing is overwritten (:ticket:`1001791`)
- Make pointcut pick up subclasses of ScalaJavaBuilder. (:ticket:`1001995`)
- Don't skip trees with transparent positions when looking for references
- Catch up with Scala Library Modularization.
- Lets the sdt.core pom.xml copy scala-reflect from the m2repo to the target folder (:ticket:`1001987`)
- Unused member warning doesn't highlight whole definition anymore (:ticket:`1001983`)
- Fix syntax coloring window crash
- Reverts logic for unloading units in askLoadedType
- Fix race condition in local rename refactoring
- Fix tests to run on Windows platform (:ticket:`1001981`)
- Throw the exception return by the the presentation compiler
- Make PC reloads start with quiescence (reloaded) - performance enhancement (:ticket:`1001388`, :ticket:`1001454`)
- Enable editor preferences (:ticket:`1001965`)
- adds constructor autocomplete. t1001272 (:ticket:`1001272`)
- Remove rename proposal from quick assists. (:ticket:`1001947`)

M1  (2013-11-05)
................

- 'Add explicit type' quick assist (:ticket:`1001951`) |new|
- Implemented completion overwrites (:ticket:`1000569`) |new|
- Corrected completion on import clause for module methods (:ticket:`1001125`)
- Corrected completion inside method arugments (:ticket:`1001218`)
- Corrected completion for no-args method (:ticket:`1001766`)
- Quick fix to create a new class (:ticket:`1000809`) |new|
- Extract local available as quick fix (:ticket:`1001801`)
- Quickfix to change the capitalization of a method/field invocation (:ticket:`1001778`)
- Fix create method quickfix exception (:ticket:`1001740`)
- Resolve exception when using type mismatch quick fix (:ticket:`1001809`)
- Race condition in pc when hyperlinking between projects (:ticket:`1001880`)
- Highlights Scaladoc macros that are wrapped in braces (:ticket:`1001836`)
- Add highlighting for variables in interpolated strings (:ticket:`1001574`, :ticket:`1001593`)
- Add semantic highlighting for singleton types (:ticket:`1001555`)
- Highlight TODO markers in the editor (:ticket:`1001450`)
- Multiline support in interpreter
- Move authorship information to AUTHORS.
- Export all packages in  MANIFEST of debug bundle
- Fix caching of SourceFiles (:ticket:`1001859`)
- source attachments for sbt dependencies (:ticket:`1001888`)
- "Restart Presentation Compiler" action (:ticket:`1000555`) |new|
- Fixed NPE when completing a wizard  (:ticket:`1001784`)
- New Class wizard injects stubs for inherited abstract methods (:ticket:`1001774`)
- Do not close string and character literals before text (:ticket:`1001652`)
- Corrected link in "Run Setup Diagnostics" Dialog (:ticket:`1001673`)
- Issue/auto closing literals (:ticket:`1001583`, :ticket:`1001600`)
- Fix rename refactoring selection/tab behavior (:ticket:`1001641`)
- Issue/string auto edit strategy (:ticket:`1001498`, :ticket:`1001491`, :ticket:`1001403`, :ticket:`1001398`)
- Use stock Scala compiler/library OSGi-fied JARs (:ticket:`1001889`)
- Opening a classfile (located outside of the build path) with no associated source no longer results in a ClassCastException (:ticket:`1001925`)
- Added source folder wizard in Scala perspective menu (:ticket:`1001577`)
- Prevented MatchError to occur on Watch Expression View (in the debugger) (:ticket:`1001933`)

3.0.3
-----

RC1 (2014-01-09)
................

- Cache info about jar files that might contain Scala classifies. (:ticket:`1001999`)
- Fix builder problem that might cause deleting output folders after a successful build. (:ticket:`1001995`)

3.0.2
-----

RC2 (2013-10-09)
................

- Open Resource dialog no longer shows Scala classfiles (:ticket:`1000260`)
- Build compiler correctly track changes in dependent project (:ticket:`1001904`)

RC1-rebuild (2013-09-24)
........................

- Update bundled Scala to 2.10.3 (final)

RC1 (2013-09-06)
................

- Eclipse 4.x support (:ticket:`1001447` and :ticket:`1001585`)
- `ScalaSourceFile.createFromPath` is now thread-safe (:ticket:`1001846`)
- Semantic highlighting for attached sources (:ticket:`1000939`)
- Updated the build compiler to use the recently released sbt 0.13.0
- Backport ScalaTokenizer.tokenize(String) (:ticket:`1001866`)
- Scala Launcher can now run main classes from a library dependency (:ticket:`1001878`)

3.0.1
-----

RC2 (2013-06-27)
................

- Run As shouldn't display an error when a binary Main can be found (:ticket:`1001760`)


RC1 (2013-06-07)
................

- StringIndexOutOfBounds exception in hyperlinking (:ticket:`1001526`)
- Race condition when parsing XML literals (:ticket:`1001708`)
- Hyperlinking to overloaded Java methods (:ticket:`1000421`)
- Correct hyperlinking inside interpolated strings (:ticket:`1001408`)
- Use the configured JDK when building a project (:ticket:`1001387`)
- Fix race conditions in compiler names (:ticket:`1001607`)
- Implemented `skipAllBreakpoints` for the Scala Debugger (:ticket:`1001437`)
- Removed all calls to `List.head` in `StructureBuilder` (:ticket:`1001707`)
- Correctly attach to a running VM (:ticket:`1001639`)
- Don't force `-Xprint-types` (:ticket:`1001677`)
- Deprecation warnings no longer accumulate (:ticket:`1001595`)
- Only consider opened projects when computing a project's direct dependencies (:ticket:`1001714`)
- Version bumping across the board, which should make our 3.0.x nightlies work better (:ticket:`1001713`)
- Improve `Open Type` This is a backport of #384. (:ticket:`1000743`, :ticket:`1001035`)
- Fix race condition during semantic highlighting (:ticket:`1001623`)
- Avoid workspace locks when building (enabling future parallel builds) (:ticket:`1001631`)
- Correctly search for threads, and don't use `Option.get` (fix JRebel compatibility) (:ticket:`1001599`)


3.0.0 - codename Helium
-----------------------

RC3 (2013-03-13)
................

- Workaround for NPE in debugger variable view when using Eclipse Juno (:ticket:`1001585`)
- Don't add arguments templates for parameterless method's completion (:ticket:`1001591`)
- Expanding variable in debugger resulted in NPE (:ticket:`1001586`)
- Return `OK_STATUS` from the semantic highlighting job when the editor is dirty. (:ticket:`1001536`)

RC2 (2013-03-06)
................

- Comply to the debugger interfaces by wrapping JDI runtime exceptions (:ticket:`1001531`)
- Lazy retrieval of Java parameter names in completions. (:ticket:`1001560`)

RC1 (2013-02-28)
................

- Fixed continuations plugin (:ticket:`1001030`)
- Fixed "todo" items disappearing in the task list (:ticket:`1001401`)
- More robust against VM exceptions. (:ticket:`1001328`) |debugger|
- Possible deadlock fix. (:ticket:`1001512`) |debugger|
- Implement 'Search test methods' in the Scala JUnit4 test runner. (:ticket:`1001474`)
- Revert "Merge pull request #269 from mads379/parsetree-1001326"
- Clean projects in a background job when project settings' change (:ticket:`1001527`)
- Semantic highlighting done in background (:ticket:`1001156`, :ticket:`1001507`, :ticket:`1001508`, :ticket:`1001493`)
- Make stepping as fast as in the JDT debugger. |debugger|
- Adds a preference for the classpath validator (:ticket:`1001482`)
- Initialize symbols for primitive types on compiler startup.
- Mark occurrence preference (and off by default) (:ticket:`1001466`)
- `requires` is not a keyword (:ticket:`1000750`)
- Disabled flaky debugger ``RemoteConnectorTest`` tests class
- Use a Job when reading classpath markers in test.
- Add checks if the underlying project closed/not exists (:ticket:`1001465`)
- Rebuild Scala projects after a global compiler settings change. (:ticket:`1001460`)
- Adds anon function and flags in cache (:ticket:`1001001`) |debugger|
- Remove `Xmigration28` setting.
- Correct regression error in ScalaCodeScanner (:ticket:`1001481`)
- Properly initialize Scala editor (quick fix/interactive error reporting) (:ticket:`1001094`, :ticket:`1001337`)
- Make the class path validator regexp stricter.
- Add test class for ScalaCodeScanner (:ticket:`1001445`)
- debugger performance improvement (part 1)  |debugger|
- Validate cross-compiled binaries on the classpath (:ticket:`1001441` :ticket:`1001249`)
- Correct syntax highlighting for while-keyword (:ticket:`1000984`)
- README now contains exhaustive developers guidelines
- Issue/bracket auto edit strategy (:ticket:`1001309`)
- Don't wait indefinitely for the hyperlink computer. (:ticket:`1001348`, :ticket:`1001251`)
- Add syntax highlighting for escape sequences in character literals (:ticket:`1001444`)
- Correct partitioning of octal sequences in character literals (:ticket:`1001443`)
- Disable useless and expensive Java goodies for Scala sources. (:ticket:`1001434`, :ticket:`1001178`)
- Issue/semantic highlighting improve (:ticket:`1001172`, :ticket:`1001372`)
- Removed old FIXMEs (:ticket:`1001310`)
- Correct auto indent behavior in comments
- Fixes #1001326 (:ticket:`1001326`)
- Ignore non-existent source classpath entries (:ticket:`1001394`)
- Refactoring of ScaladocAutoEditStrategyTest/AutoCloseBracketStrategy

M3 (released: 2012-12-20)
.........................

* Added a URL hyperlink detector - :ticket:`1001266`
* Added a :doc:`Scala JUnit Test Finder </3.0.x/features/test-finder/index>` - :ticket:`1001275`, :ticket:`1000782`
* Fixed missing implicit arguments in *implicit highlighting* - :ticket:`1001280`
* Correctly find **JUnit** tests right-clicking on a Scala source - :ticket:`1001234`, :ticket:`1001379`, :ticket:`1001380`, :ticket:`1000731`.
* Fixed crash when hovering over ``Run As`` button - :ticket:`1001304`
* Show Logical Structures working on 2.10 - :ticket:`1001315` |debugger|
* Fixed race condition in refactoring 'rename' - :ticket:`1001381`
* Fixed Juno build - :ticket:`1001374`
* Fixed race condition in the indexer - :ticket:`1001376`
* Updated the AspectJ weaving version, possibly fixing weaving crashes on startup - :ticket:`1001163`
* Correctly set breakpoints in source attachments - :ticket:`1001202` |debugger|
* Improved mark occurrences caching: less memory consumption, and always using the correct compiler - :ticket:`1001303`
* Install breakpoints correctly for inner classes and objects deriving from ``App`` - :ticket:`1001197`, :ticket:`1001367` |debugger|
* Implement enable/disable breakpoint - :ticket:`1001289` |debugger|
* Better relevance metrics in completions - :ticket:`1000485`
* Semantic highlighting for scala 'symbols - :ticket:`1001364`
* Fixed regression in the way project preferences are passed to the builder - :ticket:`1001241`, :ticket:`1001267`
* Added remote debugging - :ticket:`1001129`  |debugger|
* Fixed race condition in hyperlink computer, possibly leading to spurious errors - :ticket:`1001330`
* Properly check for build errors before launching - :ticket:`1000740`
* Fixed infinite *updated occurrences dialog* - :ticket:`1001327`
* Step filters for trait forwarders - :ticket:`1001288` |debugger|
* Fixed spurious freezes - :ticket:`1001308` |debugger|
* Graceful termination of debug actors, that fixes spurious zombie processes - :ticket:`1001233` |debugger|
* Fixed race condition in semantic highlighting that might cause spurious errors - :ticket:`1001302`
* Terminate the remote VM on user request - :ticket:`1001291` |debugger|
* Fixed Implementation Missing when viewing variables - :ticket:`1001246`  |debugger|
* Configuration dialog for step filters - :ticket:`1001088`  |debugger|
* Step filters for Scala getters/setters - :ticket:`1001137`, :ticket:`1001283`  |debugger|
* Faster completions for Java symbols - :ticket:`1001287`
* Removed dependency on Eclipse SDK - :ticket:`1001281`
* Added ``-Dsdtcore.notimeouts`` to allow for long-running tests - :ticket:`1001269`
* Fixed NPE in mark occurrences when a source is deleted - :ticket:`1001268`
* Report errors when the Sbt builder crashes - :ticket:`1001274`
* Faster scope-completions - :pull:`206`
* Filter out completions that contain ``$`` - :ticket:`1001264`
* Faster *scaladoc* auto-edit strategy - :ticket:`1001263`
* Scala Plugin Spy included in the dev-tools plugin - :pull:`203`
* Quick-fixes for type mismatches (suggest ``flatten`` or ``Option``) - :pull:`188`
* Fixed deadlock and freezes due to presentation compiler resets - :ticket:`1001102`, :ticket:`1000945`, :ticket:`1001029`

M2 (released: 2012-09-13)
.........................

* Add semantic highlighting for object members in type params - `#1001209`_
* Added semantic highlighting in context bounds, tuple/function literals, structural types, path-dependent types, `etc`_
* Fix hyperlinks to `classOf` and related - `#1001238`_
* Improvements in the incremental builder. Switched to vanilla Sbt 0.13
* Insert Java parameter names in method completions - `#1001183`_
* Make standard output/error redirection optional - `#1001133`_
* `New refactorings`
* Editor improvements: surround selection - `#1001034`_
* Support nested projects (Maven style) - `#1000881`_, `#1000734`_, `#1000621`_
* Option for Organize Imports to keep groups written by the user - `#1000846`_
* Support string interpolation literals and macro keywords in partitioner / syntax colouring. `#1001012`_
* Only show accessible members in the completion lists - `#1000784`_
* `Implicit hyperlinking`__ - `#1001002`_
* `Infer Type of Structured Selection`__
* Missing Scala library in run classpath - `#1000786`_, `#1000919`_, `#1001022`_
* Provide reusable sdt.core.tests bundle - `#1001080`_
* Problem deleting files on Windows - `#1000909`_, `#1000923`_
* Removed code generation groups from editor's context menu - `#1000972`_
* Correctly expose Scala @throw annotation to Java - `#1000707`_, `#1000800`_, `#1001005`_
* Support nested projects (Maven style) - `#1000881`_, `#1000734`_, `#1000621`_
* Fixed crash in tooltip launch button - `#1000951`_
* Made ``Run As Scala Application`` more robust - `#1000911`_, `#1001096`_
* Use the configured JDK when instantiating the presentation compiler. - `#1000820`_
* Warn the user if JDT Weaving is disabled - `#1001104`_
* Fixed NullPointerException occurring when using the ``New Application`` wizard - `#1000797`_, `#1001115`_
* Fixed Assertion exception: ``Marker property value too long`` - `#1001107`_

__ http://scala-ide.org/docs/helium/features/implicit-hyperlinking/index.html
.. _#1001002: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001002
__ http://scala-ide.org/docs/helium/features/show-type.html
.. _#1000972: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000972
.. _#1000800: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000800
.. _#1000881: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000881
.. _#1000707: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000707
.. _#1000734: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000734
.. _#1000786: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000786
.. _#1000621: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000621
.. _#1000951: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000951
.. _#1000909: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000909
.. _#1000911: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000911
.. _#1001096: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001096
.. _#1000919: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000919
.. _#1000923: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000923
.. _#1000820: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000820
.. _#1001005: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001005
.. _#1001022: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001022
.. _#1001080: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001080
.. _#1001104: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001104
.. _#1000797: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000797
.. _#1001115: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001115
.. _#1001107: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001107
.. _#1001238: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001238
.. _#1001209: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001209
.. _etc: https://github.com/scala-ide/scala-ide/pull/179
.. _#1001183: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001183
.. _#1001133: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001133
.. _New refactorings: http://scala-ide.org/docs/helium/features/new-refactoring/index.html
.. _#1001034: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001034
.. _#1000846: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000846
.. _#1001012: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1001012
.. _#1000784: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000784

M1 (released: 2012-04-13)
.........................

* Bundled with Scala 2.9.2.
* Linked refactoring actions to quickfix proposals. `pr-86`_
* Fixed incomplete package problem with auto-import on code completion. `#1000855`_
* Fixed 'invalid thread access' when creating first Java file. `#1000738`_
* Improve reference of selected elements. `pr-76`_
* Semantic highlighting support. `#1000591`_
* Fixed open declaration from context menu. `#1000920`_
* Improved closing braces management. `#1000926`_
* In development Scala Debugger. `#1000864`_
* Removed some duplicated errors. `#1000735`_
* Propagate fine-grained build information to downstream projects. `#1000894`_
* Added memory leaks test.
* Fixed problem linked to using compiler plugins, in particular the continuation plugin. `#1000901`_, `#1000908`_, `#1000917`_
* Rewriting of the REPL integration. `#1000883`_
* Move Class, Trait and Object refactoring. `#1000422`_, `#1000839`_, `#1000842`_
* Improved logging infrastructure. `#1000880`_
* Extracted external libraries from source code.
* Improved 'package.scala' support. `#1000859`_
* Implicit highlighting support. `#1000628`_
* Eclipse 3.7 Indigo support. `#1000852`_
* Fixed occasional problem with auto-import on code completion. `#1000854`_
* Improved UI for Scala completion (context information and caret position).

.. _#1000422: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000422
.. _#1000591: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000591
.. _#1000628: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000628
.. _#1000735: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000735
.. _#1000839: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000839
.. _#1000842: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000842
.. _#1000852: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000852
.. _#1000855: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000855
.. _#1000859: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000859
.. _#1000864: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000864
.. _#1000880: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000880
.. _#1000883: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000883
.. _pr-76: https://github.com/scala-ide/scala-ide/pull/76
.. _pr-86: https://github.com/scala-ide/scala-ide/pull/86

2.0.2 (release/scala-ide-2.0.x)
-------------------------------

2.0.2-final (released: 2012-07-12)
..................................

* (no changes between RC3 and the final release)


2.0.2-rc03 (released: 2012-07-04)
.................................

* Fixed NullPointerException occurring when using the ``New Application`` wizard - `#1000797`_, `#1001115`_
* Fixed Assertion exception: ``Marker property value too long`` - `#1001107`_

2.0.2-rc02 (released: 2012-06-28)
.................................

* Fixed issue with ``Run As Scala Application`` - `#1001096`_
* Warn the user if JDT Weaving is disabled - `#1001104`_

2.0.2-rc01 (released: 2012-06-22)
.................................

* Missing Scala library in run classpath - `#1000786`_, `#1000919`_, `#1001022`_
* Provide reusable sdt.core.tests bundle - `#1001080`_
* Problem deleting files on Windows - `#1000909`_, `#1000923`_
* Removed code generation groups from editor's context menu - `#1000972`_
* Correctly expose Scala @throw annotation to Java - `#1000707`_, `#1000800`_, `#1001005`_
* Support nested projects (Maven style) - `#1000881`_, `#1000734`_, `#1000621`_
* Fixed crash in tooltip launch button - `#1000951`_
* Made ``Run As Scala Application`` more robust - `#1000911`_
* Use the configured JDK when instantiating the presentation compiler. - `#1000820`_


2.0.1 (release/scala-ide-2.0.x)
-------------------------------

2.0.1-final (released: 2012-04-30)
..................................

* Bundled with Scala 2.9.2.

2.0.1-rc03 (released: 2012-04-05)
.................................

* Bundled with Scala 2.9.2 RC3.

2.0.1-rc02 (released: 2012-03-27)
.................................

* Bundled with Scala 2.9.2 RC2.

2.0.1-rc01 (released: 2012-03-22)
.................................

* Fixed 'invalid thread access' when creating first Java file. `#1000738`_
* Fixed open declaration from context menu. `#1000920`_
* Improved closing braces management. `#1000926`_
* Propagate fine-grained build information to downstream projects `#1000894`_
* Fixed occasional problem with auto-import on code completion. `#1000854`_
* Fixed problem linked to using compiler plugins, in particular the continuation plugin. `#1000901`_, `#1000908`_, `#1000917`_
* Bundled with Scala 2.9.2 RC1.

.. _#1000738: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000738
.. _#1000854: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000854
.. _#1000894: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000894
.. _#1000901: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000901
.. _#1000908: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000908
.. _#1000917: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000917
.. _#1000920: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000920
.. _#1000926: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000926

2.0.0 (release/scala-ide-2.0.0)
-------------------------------

2.0.0-final (released: 2011-12-21)
..................................

* Scala IDE plugin now signed (no more warning dialog displayed when installing the Scala IDE). `#1000719`_

.. _#1000719: http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000719

2.0.0-rc04 (released: 2011-12-13)
....................................

* Scala IDE now again compatible with Groovy IDE. `#1000798`_

.. _#1000798: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000798

2.0.0-rc03 (released: 2011-12-09)
....................................

* compatible with Spring IDE. `#1000780`_
* Incremental compilation of Java files that depend on Scala files is now correctly handled. `#1000607`_
* Corrected completion suggestions for overloaded methods. `#1000654`_
* Make Scala Interpreter view more visible. `#1000791`_
* Corrected unnecessary warning generated at start-up (*Couldn't find a match for 2.9.2.r26031-b20111119033233 in . Using default.*). `#1000793`_

.. _#1000607: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000607
.. _#1000654: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000654
.. _#1000780: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000780
.. _#1000791: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000791
.. _#1000793: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000793

2.0.0-rc02 (released: 2011-11-24)
....................................

* Better error reporting. `#1000757`_
* Fixed crash in the Eclipse Outline. `#1000748`_
* *protected* Scala entities are now exposed to Java code as *public* (this matches Scala compiler behavior). `#1000751`_
* Scan project's dependencies only for Scala projects. `#1000643`_
* Better error handling for missing class files in dependent projects.

.. _#1000643: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000643
.. _#1000748: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000748
.. _#1000751: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000751
.. _#1000757: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000757

2.0.0-rc01 (released: 2011-11-09)
....................................

* Updated the Classpath Validator to play nice with Maven project. `#1000631`_, `#1000728`_
* TODO comments are now displayed in Eclipse Task section. `#1000634`_
* Fixed an important source of instability affecting Windows Eclipse users (causing the following exception to be reported: *java.lang.IllegalArgumentException: Path for project must have only one segment.*). `#1000715`_, `#1000660`_
* Improved the Run Selection Interpreter (a project picker is now displayed when no project is selected). `#1000480`_
* The JDK selected in the project's classpath is now honored. `#1000406`_
* Resource files are copied to the output directory. `#1000636`_
* Braces and parenthesis are now (correctly) automatically matched in the editor. `#1000688`_
* Better support for dependent projects in the presentation compiler, leading to less spurious errors. `#1000699`_, `#1000645`_
* Completion support for inherited trait members in Java sources. `#1000412`_

.. _#1000406: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000406
.. _#1000412: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000412
.. _#1000480: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000480
.. _#1000634: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000634
.. _#1000631: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000631
.. _#1000636: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000636
.. _#1000645: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000645
.. _#1000660: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000660
.. _#1000688: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000688
.. _#1000699: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000699
.. _#1000715: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000715
.. _#1000728: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000728

2.0.0-beta12 (released: 2011-10-31)
......................................

* Hyperlinking on definitions between dependent projects works correctly.
* For mixed Scala/Java project, allow to change sources' compilation order (i.e., first Java and then Scala, or the other way around).
* Improved interoperability of mixed Scala/Java. `#1000652`_, `#1000670`_, `#1000678`_
* Fixed a deadlock when the presentation compiler was awaken during builds.
* Presentation compiler is notified of changes in dependent projects (no spurious errors after rebuild).
* Fixed issue in the presentation compiler that caused implicit conversions not to be applied. `#1000647`_
* Added classpath validator. An error is reported if the Scala library is missing or the version is wrong. `#1000631`_
* Improved refactoring (better support for organize/add imports). [by Mirko Stocker]
* Wizard for creating Scala Application uses now trait _App_ instead of the deprecated _Application_ trait. [by Matt Russel]

.. _#1000631: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000631
.. _#1000647: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000647
.. _#1000652: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000652
.. _#1000670: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000670
.. _#1000678: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000678

2.0.0-beta11 (released: 2011-10-03)
......................................

* Added completion proposals for any type from the classpath in the Scala editor, with automatic imports.
* Several fixes to improve interoperability of mixed Scala/Java project. `#1000594`_, `#1000568`_, `#1000524`_, `#1000586`_
* Fixes in the SBT builder regarding passing compiler options, continuations support and classpath resolution. `#1000605`_, `#1000617`_
* SBT builder is the default builder.
* Fixed Toggle Comment and Indentation for multi line string. `#1000618`_
* Fixed problem when trying to put line breakpoint in object private method. `#3271`_

.. _#3271: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/3271
.. _#1000524: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000524
.. _#1000568: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000568
.. _#1000586: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000586
.. _#1000594: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000594
.. _#1000605: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000605
.. _#1000617: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000617
.. _#1000618: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000618

2.0.0-beta10 (released: 2011-09-13)
......................................

* new SBT-based builder with inter-project dependencies. The default builder remains 'refined', but you can enable the SBT builder in Eclipse -> Preferences -> Scala  -> Compiler -> Build manager.
* better integration of mixed Scala/Java project (no more spurious errors when Java classes call Scala classes that contain annotations).
* new field in Compiler preferences for additional command line parameters, cleanup of compiler options.
* new "Show Inferred Semicolons" feature: :doc:`2.0.x/features/typingviewing`.
* syntax colouring for new REPL view.
* stop inappropriate Java save actions firing on Scala source. `#1534`_
* corrected cursor's positioning after asking completion.
* better navigation and occurrences highlighting when clicking on ``import`` clauses.
* Error Log is not in the default Scala perspective anymore.
* fixed Toggle Comment action which was incorrectly commenting an additional line. `#1000462`_

.. _#1534: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1534
.. _#1000462: https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000462

2.0.0-beta09 (released: 2011-07-21)
......................................

* better breakpoint support (fixes errors when setting breakpoints in traits coming from external libraries).
* better completions (works in many more situations, such as partially typed method names).
* better diagnostics ('no completions at all syndrome') and re-setting the Java completions flag.
* better integration with Eclipse. When clicking a Scala classfile on a stack trace report, the Scala file editor will be correctly opened and functionalities such as navigation and setting breakpoint just work.
* moved Scala completions to its own category (instead of Java Proposals). This eliminates a source of incompatibilities with the Mylyn plugin. Now you can enable/disable Scala completions from Preferences, Java/Editor/ContentAssist/Advanced Scala Completions and Scala Completions (Java sources).
* added package object wizard.

2.0.0-beta08 (released: 2011-07-12)
......................................

* fixed large memory leak in mark occurrences.
* new Run Selection REPL: edit window for commands (with history). Try it by pressing Ctrl-Shift-X inside a Scala editor to run the selected expression (or the current line). :doc:`Read more <2.0.x/features/scalainterpreter>`.
* JUnit runner finds tests in Scala files reliably (even when files are not open).
* Fixed errors shown in Java sources coming from the Scala compiler.
* Fixed crash in Java completion for Scala classes in the default (empty) package.

2.0.0-beta6
...............

* Improved stability (correct use of 'ask' calls)
* Removed dummy features used for upgrading from the old 2.7 IDE.
* Better description of the JDT weaving feature when installing it.
* Downgraded the JDT weaving plugin to the latest released version. We were using the development repository, and that caused conflicts on installation, when the user had AspectJ (or was using STS) installed -- requiring the user to unselect the JDT weaving plugin from our update site. Now the versions are the same, and no conflict is reported.
* Organize Imports improvements: various configuration options and support for adding missing imports.
* Eclipse 3.7.0 (Indigo) compatibility
* New REPL view: Launch by selecting text and pressing Ctrl+Shift+X (Cmd+Shift+X on the Mac).
    * A different key combination can be set by going to General -> Keys, and redefining the key binding for "Send Selection to REPL."
    * The interpreter can be stopped and restarted, with optional replay.
    * NOTE: the colon commands (e.g. ":implicits") that work in the terminal REPL do not yet work in this REPL view, but this will be fixed for the next beta.

2.0.0-beta2
..............

* Fixes various crashes in the structure builder, leading to un-editable files in Eclipse.
* Correctly saves preferences for the diagnostics window.
* Correctly show bean getters/setters in mixed Java/Scala projects.
* Performance improvements in structured selection.
* New formatter preference window, with preview.
* Format selection only.
* Better memory usage when closing projects.
* Allow compiler plugins in the presentation compiler.
* other bug fixes.

The full list of fixed tickets: `2.0-beta2 fixed tickets`__

__ https://scala-ide-portfolio.assembla.com/spaces/ae55a-oWSr36hpeJe5avMc/tickets/report/u33405

1.x (backport releases)
-------------------------

1.0.0.20110226-M01
.....................

* fix    : reduce freeze in editor on typing
* add    : display of implicits (result of GSoC 2010)
* add    : several tuning preferences to tune editor/plugin behavior and diseable some features
* add    : support for Eclipse Galileo (3.5) and Helios (3.6)
* add    : support of scala-2.8.1
* add    : some templates (eg : specs)
* update : Formatting Scalariform has gone from 0.0.4 to 0.0.9
* update : better Mark Occurrences
* update : better Quick Fix Imports
* update : better Structured Selections
* update : better code completion (don't forgot to enable Java Completion)
* update : better hyperlink code navigation
* delete : support of scala-2.8.0

1.0.0.20100804
..................

* Refactoring Support
* Formatting
* Mark Occurrences
* Structured Selections
* XML Syntax Highlighting
* Code Templates
* Quick Fix Imports
* new build system based on tycho, to ease contribution

see `news`__

__ http://www.scala-ide.org/2010/08/not-a-release-but-new-and-noteworthy-even-so/)

.. role:: raw-html(raw)
   :format: html

.. |debugger| replace:: :raw-html:`<span class="label info">debugger</span>`
.. |new| replace:: :raw-html:`<span class="label success">new</span>`
