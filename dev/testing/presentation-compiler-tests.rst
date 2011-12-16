Testing the Scala Presentation Compiler in isolation 
====================================================

Sometimes, tracking down a bug in the Scala IDE may be harder than what you would expect. 
Specifically, it might be difficult to tell which component is actually causing the problem: is it 
because of the Presentation Compiler (or the underlying Scala compiler), or is it a bug in the 
Eclipse side?

You are simply dealing with too much complexity. The only pragmatic solution you had so far was to 
start a debugging session and step through code. That involved a fair amount of complexity (despite 
the Scala compiler is really well written) and, worse, it's usually very time consuming.

The best solution is to simply write a test first (folks used to TDD will welcome the idea). This is 
a good practice in general (write a regression test that demonstrates the problem first, then fix 
it). 

So, if you are unsure if the presentation compiler is on blame for a specific issue, just write a 
test for it!. This will have two benefits:

* You can easily make up your mind and know if the problem is in the compiler, and
* even if the compiler is not on blame, it can be very useful to add the test to the regression suite. This way we can prevent the feature to break in the future. 

Sweet, isn't it!?

And, as we will learn, defining a new test is really simple. But, before being able to write a test, 
we need some infrastructure work.

Fork the Scala sources
----------------------

The first thing you should do is forking the `Scala Git repository 
<https://github.com/scala/scala>`_. If you are new to GitHub, `read here to learn how to fork 
a project <http://help.github.com/fork-a-repo/>`_.

Forking the project is a good idea if you later on write a few test and decide to issue a pull 
request, so that your tests can be integrated back into the Scala project.

Once you forked the project, simply open a terminal and clone your own fork to download the project's 
sources. The command for cloning the fork should be very close to the following one (mind that 
you will have to replace *<username>* with your actual Git username).

.. code-block:: bash

	$ git clone git@github.com:<username>/scala.git

Build the Scala sources
-----------------------

After cloning your fork of the Scala project, you will have to build the sources using Ant (not the 
most compelling building tool on Earth, we know. The good news is that the Scala team is moving to 
SBT right now, so soon enough we'll be relieved from using Ant). 

To build the compiler, open a terminal, go in your (local) Scala project's root folder, and then 

.. code-block:: bash

	$ ant

It will take some time to build the compiler and standard library, but once it's done you won't need 
to rebuild it anymore (unless you update the sources, of course).

Depending on the machine you are using, this make take some consistent amount of time. For instance, 
on my 2011 MacBook Pro it used to take about 30' minutes (but after I moved to a SSD, the same 
machine could build the distribution in about 10 minutes!). Just be patient and remember you are 
building the whole Scala distribution, not really the tiniest Scala project in the world :).

Hopefully, you should have successfully built the Scala compiler. It's finally time to write your 
first test for the Scala Presentation Compiler (PC).

Create a Presentation Compiler Test
-----------------------------------

Tests for the Presentation Compiler are placed in folder ``<project-root>/test/files/presentation``. 
There, you will see several other directories, each of them is associated with (at least) a file 
that matches the folder's name. You will be soon know what each folder and file is for, but what is 
important to keep in mind at this point is that the test framework works by convention. So, folders 
and files with the same name are used for running the same test.

Defining a new test boils down to performing the following steps:

1. Create a ``testXXX`` folder (the name is up to you, but please avoid spaces and don't use any esoteric character :)
2. Inside ``testXXX``, create a ``src`` folder (the name here is important, remember we use convention over configuration). In ``src`` you will put your ``XXX.scala`` test file (we will see below what this file should contain to work). The test file is the definition of your test, i.e., it will tell the test framework how you want to exercise the Presentation Compiler.
3. ``testXXX`` also needs to contain a ``Runner.scala``. In its simplest form this Scala file only contains the following object declaration (again, it's important to name the object `Test`):
   
   .. code-block:: scala 
  
     object Test extends scala.tools.nsc.interactive.tests.InteractiveTest


At this point you are almost done. Well, of course the one thing that is still missing is the test 
file (remember point 2. above). So, let's have a look at how we can create a new test, and let's do 
that with a practical example.

A while back we had some issue with completion. It was unclear why, but sometimes completion was 
simply not working as expected. Since completion is a feature that is offered by the Presentation 
Compiler (via method ``askCompletionAt``), it was difficult to tell where the problem was. We had a 
very simple code's snippet for which completion was not working in the IDE. Let's have a look at it 
(this is extracted from the bug report `#1000475 
<http://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets/1000475>`_):

.. code-block:: scala 

	class SimpleCompletion {val v = new Object; v.toS /* ask completion here won't work */ }

So, to made up our mind and understand if that was a problem with the Presentation Compiler, we 
simply created a test folder named ``ide-bug-1000475`` with the folder structure described above. 
Then we added the following test (in a ``.scala`` file) in the ``src`` directory:

.. code-block:: scala 

	class SimpleCompletion { val v = new Object; v.toS/*!*/ }

You might wonder what ``/*!*/`` is for. We refer to it as a text marker. Text markers are used by 
the testing framework to associate an action to a particular position in the source file. ``/*!*/`` 
will hence be mapped into a askCompletionAt request in the Presentation Compiler, passing the 
position of the marker as an argument.

Asking completion is not the only action you can perform in a test, there are actually other test 
markers that are defined out-of-the-box and you can immediately use, and more are likely to come in 
the future. The currently available markers are:

.. code-block:: scala 

	object CompletionMarker extends TestMarker("/*!*/")

	object TypeMarker extends TestMarker("/*?*/")

	object HyperlinkMarker extends TestMarker("/*#*/")

Each of them is very easy to use. Just look at the other tests if you have any doubt.

Now, we can try running the test. Again in the opened terminal, go under ``<project-root>/test`` 
and type

.. code-block:: bash

	$ ./partest files/presentation/ide-bug-1000475

You should see something like

.. code-block:: bash

	Testing individual files
	testing: [...]/files/presentation/ide-bug-1000475                     [  OK  ]

Well, actually if you have written your own test, it will be likely fail and not succeed. The 
reason is pretty simple, we didn't yet discussed how to define a test oracle, i.e., how do we 
assert that the test is correct/wrong?

Create the Test oracle
------------------------

Creating a test oracle is straightforward. Did you noticed the `.check` files in 
``<project-root>/test/files/presentation`` folder? 

They are the test oracles. So, go ahead and create a ``.check`` file that matches the test folder's 
name (for our running example it would be ``ide-bug-1000475.check``). Inside, you simply have to 
append the output that was printed in the console when the test failed (some ``sed`` might be needed 
to clean up the text ;), assuming you think the output is correct (if the output is incorrect, you 
might just have discovered a bug in the Presentation Compiler!).

Re-run the test and hopefully it should pass. If it does succeed, then the odds are that you have to 
fix the issue in the Eclipse side, meaning that the Scala compiler is not on blame. 

The bad news is that you will need some more digging to narrow down the cause. But, the good news is 
that now you are now one step closer to the solution!
