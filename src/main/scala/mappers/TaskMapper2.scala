package mappers

import java.net.URI

import mappers.UtilityClass.{getElementFromXML, getXMLString}
import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import org.slf4j.{Logger, LoggerFactory}

class TaskMapper2 extends Mapper[LongWritable, Text, Text, IntWritable] {

  private val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  override def map(key: LongWritable, value: Text, con: Mapper[LongWritable, Text, Text, IntWritable]#Context): Unit = {
    logger.info("Mapping for task 2 | start")
    val xmlString = getXMLString(value.toString)
    val authors = getElementFromXML(xmlString,"author") ++ getElementFromXML(xmlString,"editor")
    val date = getElementFromXML(xmlString, "year")
    date.nonEmpty match {
      case true => val year = date.head.toInt
        authors.foreach(author => con.write(new Text(author), new IntWritable(year)))
      case false =>
    }
    logger.info("Mapping for task 2 | end")
  }

}