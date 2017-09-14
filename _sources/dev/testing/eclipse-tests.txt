Testing Eclipse integration
===========================

All tests are grouped under the ``org.scala-ide.sdt.core.tests`` project, following standard 
practice for Eclipse plug-in tests (in OSGi terms, this module is a ‘bundle fragment’ of 
``org.scala-ide.sdt.core``, but this means only that it has access to all packages defined by 
``org.scala-ide.sdt.core``).

One can run tests in two ways:

* using the Eclipse JUnit Plugin Test runner (with Equinox weaving, of course)
* using maven/tycho

Both ways launch a new Eclipse instance, installs the ``sdt.core`` and ``sdt.core.tests`` bundles in 
the target instance, creates a clean (empty) workspace, and launches the test runner. Of course, 
``sdt.core`` can not run by itself: it needs a lot of other bundles, such as the eclipse core 
bundles and the JDT bundles. Depending on the way the test is launched, these dependencies are 
provided differently:

* using the existing (running) Eclipse instance, and adding all existing plug-ins to the launched instance (they can be enabled/disabled individually in the Run Configurations dialog)
* using the declared dependencies in META-INF/MANIFEST.MF. Tycho will download them using the configured p2 repositories, in the same way that maven resolves library dependencies


Individual tests
----------------

An integration test is usually set up by copying an existing project from 
``org.scala-ide.sdt.core.tests/test-workspace`` to the target (clean) workspace, then running a 
number of ``@Test`` methods on it. The setup step is taken care of by subclassing 
`TestProjectSetup 
<https://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core.tests/src/scala/tools/eclipse/testsetup/TestProjectSetup.scala>`_, 
usually with an object. For example, the `StructureBuilderTest 
<https://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core.tests/src/scala/tools/eclipse/structurebuilder/StructureBuilderTest.scala>`_ 
declares

.. code-block:: scala

	object StructureBuilderTest extends testsetup.TestProjectSetup("simple-structure-builder")
	
This gives a number of convenience handles that make it easy to retrieve elements of interest later:

* ``project`` it is the (`ScalaProject <https://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core/src/scala/tools/eclipse/ScalaProject.scala>`_) associated to the copied project.
* ``srcPackageRoot`` is the `IPackageFragmentRoot <http://publib.boulder.ibm.com/infocenter/iadthelp/v6r0/index.jsp?topic=/org.eclipse.jdt.doc.isv/reference/api/org/eclipse/jdt/core/IPackageFragmentRoot.html>`_ instance of the *src* folder.

You can use compilationUnit with a full path to retrieve the `ICompilationUnit 
<http://publib.boulder.ibm.com/infocenter/iadthelp/v6r0/index.jsp?topic=/org.eclipse.jdt.doc.isv/reference/api/org/eclipse/jdt/core/ICompilationUnit.html>`_ 
of the corresponding file.

Note that these handles are pure JDT API elements. Using them, you can build your test methods that 
exercise the JDT and Scala integration layers. For instance, to test that annotations are correctly 
reported by the `ScalaStructureBuilder 
<http://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core/src/scala/tools/eclipse/javaelements/ScalaStructureBuilder.scala>`_, 
one would write the following method:

.. code-block:: scala

    @Test def testAnnotations() {
      val annotsPkg = srcPackageRoot.getPackageFragment("annots");
      assertNotNull(annotsPkg)
      val cu = annotsPkg.getCompilationUnit("ScalaTestSuite.scala")
      assertTrue(cu.exists)
      val tpe = cu.findPrimaryType()
      assertNotNull("Primary type should not be null", tpe)
      val m1 = tpe.getMethod("someTestMethod", Array())
      val m2 = tpe.getMethod("anotherTestMethod", Array())
      
      assertTrue(m1.getAnnotations.length == 1)
      assertTrue(m1.getAnnotation("Test").exists)
      assertTrue(m2.getAnnotations.length == 1)
      assertTrue(m2.getAnnotation("Test").exists)
    }

This assumes the project has a file called ``ScalaTestSuite.scala``, under ``src/annots``. Note the 
use of the JDT API again. Equivalently, one could use the convenience method 
``compilationUnit("annots/ScalaTestSuite.scala")``.


Resolving positions
-------------------

What you’ve seen so far works well for tests that operate on whole compilation units. However, many 
times an operation needs to be performed on a certain element, at a certain position in the source 
file. Positions are integer offsets in a source file, and one way would be to hard code them in the 
testing code. However, this is fragile, and makes the test cumbersome to write. A better way is to 
use markers in the source that is subject to testing, and convert them automatically to the 
corresponding offset.


Markers
-------

A marker is any string, but we use block comments with a character in between: ``/*!*/``. Such a 
marker has the advantage that it keeps the source compilable. Have a look at the hyperlink test 
project:

.. code-block:: scala

   class SimpleHyperlinking {
     type Tpe[T] = List/*^*/[T]
  
     def foo(xs: Tpe/*^*/[Int]) = {
       val arr = Array/*^*/(1, 2, 3)
       val sum = xs.sum/*^*/
       val x: String/*^*/ = "Hello, world"
     }
   }

Each marker will be used by our test method as a position to ask for hyperlinking. In the test 
method, we retrieve these positions (using `SDTTestUtils 
<https://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core.tests/src/scala/tools/eclipse/testsetup/SDTTestUtils.scala>`_) 
and pass them to the hyperlink detector:

.. code-block:: scala

    val contents = unit.getContents
    val positions = SDTTestUtils.positionsOf(contents, "/*^*/")
    // ..
    val detector = new ScalaHyperlinkDetector
    for (pos <- positions) {
      val wordRegion = ScalaWordFinder.findWord(unit.getContents, pos - 1)
      val links = detector.scalaHyperlinks(unit, wordRegion)
      println("Found links: " + links)
      assertTrue(links.isDefined)
      assertEquals(1, links.get.size)
    }


Advanced markers
----------------

Sometimes a simple marker does not carry enough information. Consider testing the mark occurrences 
functionality: each word that is highlighted may appear a different number of times in the source. 
One can associate a number with a marker by using `SDTTestUtils.markersOf 
<https://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core.tests/src/scala/tools/eclipse/testsetup/SDTTestUtils.scala>`_. 

Consider this example:

.. code-block:: scala

    class DummyOccurrences(param: Int, func/*<2*/: (Int/*<5*/, Int) => Int) {
      type T/*<2*/ = Int

      def sum(xs: List[T]) = {
        xs.foldLeft(param/*<3*/)(_ + _)
        for (j <- xs) {
          (param /: xs)(func)
        }
      }
    }

In this test file, we expect that `func` will be highlighted 2 times, `Int` 5 times, and so on. The 
test method will use the parsed integer to assert the correct number of matches is reported by the 
`ScalaOccurrencesFinder 
<https://github.com/scala-ide/scala-ide/blob/master/org.scala-ide.sdt.core/src/scala/tools/eclipse/markoccurrences/ScalaOccurrencesFinder.scala>`_.

.. code-block:: scala

    val contents = unit.getContents
    val positions = SDTTestUtils.markersOf(contents, "<")
    
    println("checking %d positions".format(positions.size))

and the actual test:

.. code-block:: scala

    for ((pos, count) <- positions) {
      println("looking at position %d for %d occurrences".format(pos, count))
      val region = ScalaWordFinder.findWord(contents, pos - 1)
      println("using word region: " + region)
      val finder = new ScalaOccurrencesFinder(unit, region.getOffset, region.getLength)
      val occurrences = finder.findOccurrences
      assertTrue(finder.findOccurrences.isDefined)
      assertEquals(count, occurrences.get.locations.size)
    }


Running tests within Eclipse
----------------------------

To run the tests inside Eclipse you need to :ref:`install the Equinox Weaving Launcher <setup_install-equinox-weaving-launcher>` plug-in for Eclipse. 
Once you have installed the plug-in, running a test in headless mode boils down to the following 
steps:

* Open Run Configurations and double click on JUnit Plug-in Test with Equinox Weaving (which should have appeared after installing the above mentioned plug-in).
* In the Test tab, fill in the information about the test you want to run.
* In the Main tab, under Program to Run, check Run an application and select *[No Application] – Headless Mode*.
* In the Arguments tab, make sure to pass ``-Dsdtcore.headless`` in the VM arguments.
* In the Plug-ins tab, make sure that bundle ``org.scala-ide.sdt.core.tests`` is selected (or the test wont be able to find the test class file)

At this point you should be good to run the test.