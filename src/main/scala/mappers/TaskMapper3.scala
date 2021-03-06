package mappers

import java.net.URI

import mappers.UtilityClass.{getElementFromArray, getElementFromXML, getXMLString}
import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import org.slf4j.{Logger, LoggerFactory}

class TaskMapper3 extends Mapper[LongWritable, Text, Text, Text] {

  private val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  override def map(key: LongWritable, value: Text, con: Mapper[LongWritable, Text, Text, Text]#Context): Unit = {
    logger.info("Mapping for task 3 | start")
    val xmlString = getXMLString(value.toString)
    val venue_list = Array("journal", "booktitle", "publisher", "www","school")
    // fetching the venue from a list of possible venues
    val venue_val = getElementFromArray(venue_list,xmlString)
    val publications = getElementFromXML(xmlString,"title")
    // writing only when 1 author is present
    publications.nonEmpty && venue_val.nonEmpty match {
      case true =>
        val authors = getElementFromXML (xmlString, "author") ++ getElementFromXML (xmlString, "editor")
        authors.size match {
        case 1 => con.write (new Text (venue_val), new Text (publications.head) )
        case _ =>
        }
      case false =>
    }
    logger.info("Mapping for task 3 | end")
  }


}

