package reducers

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer
import org.slf4j.{Logger, LoggerFactory}

import scala.::
import scala.collection.convert.ImplicitConversions.`iterable AsScalaIterable`

class TaskReducer2 extends Reducer[Text,IntWritable,Text,IntWritable] {

  private val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  override
  def reduce(word:Text, values:java.lang.Iterable[IntWritable], con:Reducer[Text,IntWritable,Text,IntWritable]#Context): Unit = {
    logger.info("Reducing for task 2 | start")
    var years = List[Int]()
    values.forEach(value => years = value.get :: years)
    years = years.sorted

    var yearCount = 0
    var prevYear = years.head
    //checking contigious 10 numbers
    years.size>=10 match {
      case true =>
        years.foreach (year => {
          year == prevYear + 1 match {
            case true => yearCount += 1
            case false => year != prevYear match {
              case true => yearCount = 0
              case false =>
            }
          }
        prevYear = year
        })
      case false =>
    }
    yearCount>=10 match{
      case true => con.write(word, new IntWritable(yearCount))
      case false =>
    }
    logger.info("Reducing for task 2 | end")
  }
}