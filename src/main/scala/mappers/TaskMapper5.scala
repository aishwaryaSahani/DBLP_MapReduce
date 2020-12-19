package mappers

import java.net.URI

import mappers.UtilityClass.{getElementFromXML, getXMLString}
import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import org.slf4j.{Logger, LoggerFactory}

class TaskMapper5 extends Mapper[LongWritable, Text, Text, Text] {

  private val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  override def map(key: LongWritable, value: Text, con: Mapper[LongWritable, Text, Text, Text]#Context): Unit = {
    logger.info("Mapping for task 5 | start")
    val xmlString = getXMLString(value.toString)

    val authors = getElementFromXML(xmlString,"author") ++ getElementFromXML(xmlString,"editor")
    // using combinations to get all possible pair of authors
    authors.combinations(2).foreach ( pair=> {
      con.write(new Text(pair.head), new Text(pair.last))
      con.write(new Text(pair.last), new Text(pair.head))
    })
    logger.info("Mapping for task 5 | end")
  }
}