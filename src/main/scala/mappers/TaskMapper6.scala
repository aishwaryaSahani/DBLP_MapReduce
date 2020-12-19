package mappers

import java.net.URI

import mappers.UtilityClass.{getElementFromXML, getXMLString}
import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import org.slf4j.{Logger, LoggerFactory}

class TaskMapper6 extends Mapper[LongWritable, Text, Text, IntWritable] {

  private val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  override def map(key: LongWritable, value: Text, con: Mapper[LongWritable, Text, Text, IntWritable]#Context): Unit = {
    logger.info("Mapping for task 6 | start")
    val xmlString = getXMLString(value.toString)

    val authors = getElementFromXML(xmlString,"author") ++ getElementFromXML(xmlString,"editor")
    authors.size match {
      case 1 => con.write(new Text(authors.head), new IntWritable(1))
      case _ =>
    }
    logger.info("Mapping for task 6 | end")
  }
}