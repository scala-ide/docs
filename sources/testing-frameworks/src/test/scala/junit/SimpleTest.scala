package junit

import scala.collection.mutable.ListBuffer
import org.junit.Test
import org.junit.Assert._

class SimpleTest {
  
  @Test def +() {
    val list= new ListBuffer[String]
    list + "one"
    assertEquals("Wrong list state", ListBuffer("one"), list)
  }
  
  @Test def isEmptyTrue() {
    val list= new ListBuffer[String]
    assertTrue("list should be empty", list.isEmpty)
  }
  
  @Test def isEmptyFalse() {
    val list= ListBuffer("one")
    assertFalse("list should not be empty", list.isEmpty)
  }

}