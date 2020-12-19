package reducers

import java.io.IOException

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.convert.ImplicitConversions.{`iterable AsScalaIterable`, `map AsJavaMap`}

class TaskReducer6 extends Reducer[Text,IntWritable,Text, IntWritable] {
  //storing the result in treemap and displaying the result at cleanup i.e. reduction completion
  var treeMap2 = scala.collection.mutable.TreeMap[Int, String]()(implicitly[Ordering[Int]].reverse)
  private val logger: Logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  @throws[IOException]
  @throws[InterruptedException]
  override def reduce(word:Text, values:java.lang.Iterable[IntWritable], con:Reducer[Text,IntWritable,Text,IntWritable]#Context): Unit = {
    logger.info("Reducing for task 6 | start")
    var sum = 0
    values.forEach(value => sum += value.get )
    treeMap2 += ( sum -> word.toString )
    logger.info("Reducing for task 6 | end")
  }

  @throws[IOException]
  @throws[InterruptedException]
  override protected def cleanup(context: Reducer[Text,IntWritable,Text,IntWritable] #Context): Unit = {
    treeMap2.take(100).entrySet.foreach(entry => {
      context.write(new Text(entry.getValue), new IntWritable(entry.getKey))
    })
  }
}