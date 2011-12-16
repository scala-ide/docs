.. _repository-organization_page:

Repository organization
=======================

Ideally, the project's directory structure should be close to the following:

.. code-block:: text

  | - master
  | - experimental
            | - gwt-support
  | - issue
            | - scala-java-interop-1000508
            | - scaladoc-hover-1000210
  | - platform
            | - indigo-3.7
            | - juno-3.8
  | - release
            | - scala-ide-2.0.0
            | - scala-ide-2.0.x

* ``master``: The main development branch.
* ``experimental``: Hosts branches for trying out ideas.
* ``issue``: All branches under `issue` are related to an Assembla ticket. The ticket's number must 
  be suffixed to the name of the branch.
* ``platform``: Branches for experimental support of future Eclipse platforms (with different JDT). 
* ``release``: Development branches for a target release. Stability is an important requirement for 
  all branches living here.

Branches should be named using the following :ref:`convention <workflow_branch-name-convention>`.

