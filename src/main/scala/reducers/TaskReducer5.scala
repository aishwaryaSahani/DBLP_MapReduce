package reducers

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.convert.ImplicitConversions.{`iterable AsScalaIterable`, `map AsJavaMap`}

class TaskReducer5 extends Reducer[Text,Text,Text,Text] {

  //storing the result in treemap and displaying the result at cleanup i.e. reduction completion
  var treeMap2 = scala.collection.mutable.TreeMap[Int, String]()(implicitly[Ordering[Int]].reverse)
  private val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  override def reduce(word:Text, values:java.lang.Iterable[Text], con:Reducer[Text,Text,Text,Text]#Context): Unit = {
    logger.info("Reducing for task 5 | start")
    var authorSet:Set[String] = Set()
    values.foreach(author => authorSet += author.toString)
    treeMap2 += ( authorSet.size -> word.toString )
    treeMap2.size() > 100 match {
      case true => treeMap2.remove(treeMap2.firstKey)
      case false =>
    }
    logger.info("Reducing for task 5 | end")
  }

  override protected def cleanup(context: Reducer[Text,Text,Text,Text] #Context): Unit = {
    treeMap2.entrySet.foreach (entry => {
      val count = entry.getKey
      val word = entry.getValue
      context.write(new Text(word), new Text(count.toString))
    })
  }

}