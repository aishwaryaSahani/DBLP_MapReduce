package reducers

import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.convert.ImplicitConversions.`iterable AsScalaIterable`
import scala.collection.immutable.ListMap

class TaskReducer1 extends Reducer[Text, Text, Text, Text] {

  private val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  override
  def reduce(word:Text, values:java.lang.Iterable[Text], con:Reducer[Text, Text, Text, Text]#Context): Unit = {
    logger.info("Reducing for task 1 | start")
    var authorMap = collection.mutable.Map[String, Int]()
    values.foreach(author => {
      !authorMap.contains(author.toString) match{
      case true => authorMap = authorMap + (author.toString -> 1)
      case false =>  authorMap = authorMap + (author.toString -> (authorMap(author.toString) + 1))
      }
    })
    //sorting map based on value
    val res = ListMap(authorMap.toSeq.sortWith(_._2 > _._2):_*)
    res.take(10).foreach{ authorValue =>
      con.write(word, new Text(authorValue._1))
    }
    logger.info("Reducing for task 1 | end")
  }
}