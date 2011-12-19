.. _workflow_page:

Workflow
========

In this page we discuss the workflow for both external contributors and committers. The difference 
between the two is very thin, and it boils down to having the rights for integrating patches (pull 
requests, in GitHub terminology) in the mainline.

We assume you are comfortable with both Git and GitHub. If you need an introduction to these 
essential tools, we suggest you to start by reading :ref:`git-github_page`.


External contributors
---------------------

.. note::

	If you are an external contributor, the first thing you want to do is :ref:`forking the Scala IDE 
	repository <setup_fork-the-project>`.

In your own fork, you are clearly free to adopt the workflow that better suits you. Though, the 
moment you decide to send a pull request (so that your changes can be integrated in the Scala IDE 
project), you need to make sure that both the branch name and your code match the required standards. 
That is why we suggest you to follow the conventions discussed in this section also in your 
own fork, it will just make it simpler and faster to push your changes in the main project.

There are two, very simple, steps that you should do before starting any work:

* First, discuss with the core dev team what you would like to work on. There are several reasons 
  why you should do this, but mainly it is about making sure that everyone knows what you are doing, 
  so that we can avoid duplicating effort (which would be indeed a shame), and you may get some precious 
  feedback, which can help you being more effective and avoid wasting time. As usual, to get in 
  touch just drop a note in the `scala-ide-dev 
  <https://groups.google.com/group/scala-ide-dev?hl=en>`_ mailing list.

* Second, make sure that a ticket describing the defect/feature you want to tackle exists in our 
  `Assembla issue tracker <https://scala-ide-portfolio.assembla.com/spaces/scala-ide/tickets>`_. If you 
  can't find one, just go ahead and create it. Once you have a ticket, assign it to you. In short, 
  we would like defect/enhancement/feature to be driven by tickets as much as possible, as this 
  help us keeping track of changes between each release.

Alright, now it's time to create a new branch (standard git workflow).


.. _workflow_branch-name-convention:

Branch name convention
......................

The convention for naming a branch is straightforward, the name should convey what the branch is 
about (i.e., a short description) and the ticket number should be suffixed. Words are separated by 
dashes (``-``). For instance, ``scala-java-interop-1000508`` is a perfectly valid name.


Ready to push your changes?
...........................

When you are done working on a ticket, make sure that:

* Your code is clean and documented. And you have made your best effort to decouple UI elements from 
  core logic (that enables you to create tests for the core logic).

* You have done your best to create a set of tests that exercise the implemented feature. (Read 
  :ref:`testing_regression-test-suite` for learning how to create tests for the Scala IDE).

* Your branch compiles against Scala 2.9.x and 2.10 (read :ref:`building_run-the-build` for 
  learning how to build the Scala IDE with different versions of Scala). Of course, that also 
  implies all tests succeeded.
  
* Rebase your branch on the latest upstream (read `here`__ for more information on how to do this).

__ https://github.com/diaspora/diaspora/wiki/Git-Workflow

If you get stuck with any of the above, just write a note in the `scala-ide-dev 
<https://groups.google.com/group/scala-ide-dev?hl=en>`_ mailing list. We are very keen in making 
sure you can integrate your awesome work back in the project's mainline, so we will be very happy 
to help you fix any of the above points.


Send us a pull request
......................

Once you think your work is ready to be integrated, send us a pull request (read `here`__ for how 
to do this). When sending a pull request, contributors will review your code. There might be a few 
iterations (code review, update code) before your pull request is merged in the mainline. Don't get 
discouraged, we absolutely want your code to be in the mainline, but we also need to make sure that 
the quality standards are met. In the end, all we want is simply a better Scala IDE and a nice 
codebase, so that we can make it as simple as possible for new contributors to enter the project.

__ http://help.github.com/send-pull-requests/

Of course, after sending a pull request, you can immediately start working on a new task ;)


Committers
----------

.. note::

	
	This section assumes that you know how the :ref:`repository is organized 
	<repository-organization_page>`.
	

The workflow for committers is very similar to the one described in the above 
`External contributors`_ section, with the only difference that you don't need to fork the project 
and you can push your branches directly in the main Scala IDE git repository.

It is easy to decide where your branch should live:

* If you are experimenting a new idea, create it under ``experimental``.
* If you want to work on a defect/enhancement/feature, create it under ``issue``. If you can't find 
  a ticket describing what you want to work on, create a new one before starting to do any work. For 
  each branch created under issue it is mandatory to have a ticket linked to it.

Overall, we expect to have very few (possibly none at all) branches living in ``experimental``, as we 
would like defect/enhancement/feature to be driven by tickets as much as possible.

Branches should be named using the same convention described in :ref:`workflow_branch-name-convention`.

When creating a new branch under ``issue``, you should push your branch remotely as soon as 
possible. Don't be afraid of sharing bad code, none will blame you for that. The only moment when 
code quality does matter is when you ask to merge your changes into the mainline (``master``
branch). Until that moment you are free of committing almost anything on your own branch (but please 
do not push binaries in remote branches, as they will considerably increase the repository's 
footprint).

When you are done working on a branch, create a pull request for it.

Once the review process is over, you are good to merge. But before doing so, you need to know on 
what branches you should merge to. In general, you will always need to merge with master. Though, 
depending on the ticket you have been working on, you may also want to merge in one of the release 
branches.

A new release branch is created when it is time to start working on stabilizing the Scala IDE for a 
final release. For instance, at the time of this writing, work is being done for releasing 2.0.0. 
Consequently, when working on a ticket with its Milestone set to 2.0, you will always need to merge 
your work with both master and ``scala-ide-2.0.x``. If the ticket's Milestone is not 2.0, you may still 
be allowed to merge in the ``scala-ide-2.0.x`` release branch, depending on the content of your changes. 
The best way to know whether you should merge in a release branch is, again, to open a discussion in 
the `scala-ide-dev <https://groups.google.com/group/scala-ide-dev?hl=en>`_ mailing list.

The following are the steps you should follow when your work is ready to be integrated in the 
mainline:

* before merging, rebase your branch (this step is optional, but it keeps the repository's history cleaner),

* merge your branch into master (and in a release branch if needed. Look at the ticket's Milestone field),

* close the ticket, and

* delete the remote branch associated with the above closed ticket.

Now you are ready to hack on a new ticket ;)

If anything above is unclear, please make sure to ask in the `scala-ide-dev 
<https://groups.google.com/group/scala-ide-dev?hl=en>`_ mailing list for clarification.