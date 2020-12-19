import java.net.URI

import junit.framework.TestCase
import mappers.UtilityClass.{getElementFromArray, getElementFromXML, getXMLString}
import org.junit.Assert.{assertEquals, assertNotNull, assertTrue}

class UtilityClassTest extends TestCase {
  def testGetElementFromXML() {
    val xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n              <!DOCTYPE dblp SYSTEM \"file:/C:/Users/aishs/IdeaProjects/aishwarya_sahani_hw2/target/scala-2.13/classes/dblp.dtd\">\n              <dblp><article mdate=\"2019-10-25\" key=\"tr/gte/TR-0263-08-94-165\" publtype=\"informal\">\n<author>Frank Manola</author>\n<title>An Evaluation of Object-Oriented DBMS Developments: 1994 Edition.</title>\n<journal>GTE Laboratories Incorporated</journal>\n<volume>TR-0263-08-94-165</volume>\n<month>August</month>\n<year>1994</year>\n<url>db/journals/gtelab/index.html#TR-0263-08-94-165</url>\n</article></dblp>"
    val authorName = getElementFromXML(xml:String, "author").head
    assertEquals(authorName,"frank manola" )
  }
  def testGetXMLString() {
    val xml = "<www mdate=\"2002-01-03\" key=\"www/org/w3/dom\">     <title>W3C: Document Oject Model (DOM)</title> <url>http://www.w3.org/DOM/</url> </www>"
    val xmlString = getXMLString(xml)
    assertNotNull(xmlString)
  }
  def testGetElementFromArray() {
    val xml = "<dblp><article mdate=\"2019-10-25\" key=\"tr/gte/TR-0263-08-94-165\" publtype=\"informal\">\n<author>Frank Manola</author>\n<title>An Evaluation of Object-Oriented DBMS Developments: 1994 Edition.</title>\n<journal>GTE Laboratories Incorporated</journal>\n<volume>TR-0263-08-94-165</volume>\n<month>August</month>\n<year>1994</year>\n<url>db/journals/gtelab/index.html#TR-0263-08-94-165</url>\n</article></dblp>"
    val xmlString = getXMLString(xml)
    val venue_list = Array("journal", "booktitle", "publisher", "www")
    val publicationValue = "gte laboratories incorporated"
    assertEquals(getElementFromArray(venue_list:Array[String], xmlString:String),publicationValue )
  }
}
