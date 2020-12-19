package mappers

import java.net.URI

import scala.xml.XML

object UtilityClass {

  /**
   * Get element list from element in the xml
   * @param xml
   * @param element
   * @return
   */
  def getElementFromXML(xml:String, element:String) : List[String] ={
    val parent = XML.loadString(xml)
    val elementList  =  (parent \\ element).map(el => el.text.toLowerCase.trim).toList
    elementList
  }

  /**
   * Generate valid xml string from shards
   * @param xml
   * @return
   */
  def getXMLString(xml:String)  : String= {
    val dblpdtd: URI = getClass.getClassLoader.getResource("dblp.dtd").toURI
    s"""<?xml version="1.0" encoding="ISO-8859-1"?>
              <!DOCTYPE dblp SYSTEM "$dblpdtd">
              <dblp>""" + xml + "</dblp>"
  }

  /**
   * Get single element value from list of options
   * @param list
   * @param xmlString
   * @return
   */
  def getElementFromArray(list:Array[String], xmlString:String) : String ={
    list.foreach(element => {
      val val_list = getElementFromXML(xmlString, element)
      val_list.nonEmpty match {
        case true => return val_list.head
        case false =>
      }
    })
    ""
  }

}
