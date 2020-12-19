package reducers

import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.convert.ImplicitConversions.`iterable AsScalaIterable`

class TaskReducer3 extends Reducer[Text,Text,Text,Text] {

  private val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  override
  def reduce(word:Text, values:java.lang.Iterable[Text], con:Reducer[Text,Text,Text,Text]#Context): Unit = {
    logger.info("Reducing for task 3 | start")
    values.map (value => con.write(word, value))
    logger.info("Reducing for task 3 | end")
  }
}