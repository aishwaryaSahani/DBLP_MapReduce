import com.typesafe.config.ConfigFactory
import junit.framework.TestCase
import org.junit.Assert.assertEquals

import scala.collection.immutable.ListMap

class Functionality_implTest extends TestCase {
  def testMapSortingToFetchTopNrecords() {
    var authorMap = collection.mutable.Map[String, Int]()
    authorMap += ("frank manola" -> 5)
    authorMap += ("tolga yurek" -> 1)
    authorMap += ("zaiming yang" -> 3)
    // Map sorted in descending order to fetch top 10 elements
    val res = ListMap(authorMap.toSeq.sortWith(_._2 > _._2): _*)
    res.take(1).foreach { authorValue =>
      assertEquals(authorValue._1,"frank manola")
    }
  }

  def testMapLargestElement() {
    var publicationsMap = collection.mutable.Map[String, Int]()
    publicationsMap += ("Nucleic Acids Research" -> 5)
    publicationsMap += ("J. Autom. Reasoning" -> 4)
    publicationsMap += ("Scientific Programming" -> 8)
    assertEquals(publicationsMap.maxBy(_._2)._1,"Scientific Programming")
  }

  def testConfigurationValues(): Unit ={
    val conf = ConfigFactory.load()
    assertEquals(",", conf.getString("separator"))
  }

}
