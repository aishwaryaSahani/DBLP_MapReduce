package reducers

import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.convert.ImplicitConversions.`iterable AsScalaIterable`

class TaskReducer4 extends Reducer[Text,Text,Text,Text] {

  private val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  override
  def reduce(word:Text, values:java.lang.Iterable[Text], con:Reducer[Text,Text,Text,Text]#Context): Unit = {
    logger.info("Reducing for task 4 | start")
    var publicationsMap = collection.mutable.Map[String, Int]()
    values.foreach(publication => {
      !publicationsMap.contains(publication.toString) match {
        case true => publicationsMap = publicationsMap + (publication.toString -> 1)
        case false => publicationsMap = publicationsMap + (publication.toString -> (publicationsMap(publication.toString) + 1))
      }
    })
    // selecting max element based on value
    con.write(word, new Text(publicationsMap.maxBy(_._2)._1))
    logger.info("Reducing for task 4 | end")
  }
}