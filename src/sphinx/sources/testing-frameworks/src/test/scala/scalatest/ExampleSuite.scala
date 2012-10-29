package scalatest

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ExampleSuite extends FunSuite with BeforeAndAfter {
 
  var stack: Stack[Int] = _

  before {
    stack = new Stack[Int]
  }

  test("pop is invoked on a non-empty stack") {
 
    stack.push(1)
    stack.push(2)
    val oldSize = stack.size
    val result = stack.pop()
    assert(result === 2)
    assert(stack.size === oldSize - 1)
  }
 
  test("pop is invoked on an empty stack") {
 
    intercept[NoSuchElementException] {
      stack.pop()
    }
    assert(stack.isEmpty)
  }
}