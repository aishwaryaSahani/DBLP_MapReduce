import com.typesafe.config.ConfigFactory
import mappers.{TaskMapper1, TaskMapper2, TaskMapper3, TaskMapper4, TaskMapper5, TaskMapper6}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.slf4j.{Logger, LoggerFactory}
import reducers.{TaskReducer1, TaskReducer2, TaskReducer3, TaskReducer4, TaskReducer5, TaskReducer6}

import scala.jdk.CollectionConverters.CollectionHasAsScala

object Functionality_Impl{
  def main(args: Array[String]): Unit = {
    (new Functionality_Impl).mapReduce(args)
  }
}

class Functionality_Impl {

  private val logger: Logger = LoggerFactory.getLogger(classOf[Functionality_Impl])

  /**
   * Map reduce program which initializes all the configurations and runs map-reduce tasks
   * @param args
   */
  def mapReduce(args: Array[String]): Unit = {

    logger.info("Setting up the Map Reduce configurations | Start")
    System.setProperty("entityExpansionLimit", String.valueOf(Integer.MAX_VALUE))
    // Loading typeconfig
    val conf = ConfigFactory.load()
    val job_names: List[String] = conf.getStringList("job_names").asScala.toList
    val configuration = new Configuration
    configuration.set("mapred.textoutputformat.separator", conf.getString("separator"))
    configuration.set("xmlinput.start", conf.getString("START_TAGS"))
    configuration.set("xmlinput.end", conf.getString("END_TAGS"))
    val inputFolder = new Path(args(0))
    logger.info("Setting up the Map Reduce configurations | End")

    mapReduceTask1(configuration,inputFolder,args(1),job_names(0))
    mapReduceTask2(configuration,inputFolder,args(1),job_names(1))
    mapReduceTask3(configuration,inputFolder,args(1),job_names(2))
    mapReduceTask4(configuration,inputFolder,args(1),job_names(3))
    mapReduceTask5(configuration,inputFolder,args(1),job_names(4))
    mapReduceTask6(configuration,inputFolder,args(1),job_names(5))
  }

  /**
   * Executes task 1
   * @param configuration
   * @param inputFolder
   * @param output
   * @param job_name
   */
  def mapReduceTask1(configuration: Configuration, inputFolder: Path, output: String, job_name: String) :Unit = {
    logger.info("Execution of Map Reduce Task 1 | start")
    val outputFolder1 = new Path(output + "1")
    val job1 = new Job(configuration, job_name)
    job1.setInputFormatClass(classOf[XmlInputFormatWithMultipleTags])
    job1.setJarByClass(classOf[Functionality_Impl])
    job1.setMapperClass(classOf[TaskMapper1])
    job1.setReducerClass(classOf[TaskReducer1])
    job1.setOutputKeyClass(classOf[Text])
    job1.setOutputValueClass(classOf[Text])
    FileInputFormat.addInputPath(job1, inputFolder)
    FileOutputFormat.setOutputPath(job1, outputFolder1)
    job1.waitForCompletion(true)
    logger.info("Execution of Map Reduce Task 1 | end")
  }

  /**
   * Executes task 2
   * @param configuration
   * @param inputFolder
   * @param output
   * @param job_name
   */
  def mapReduceTask2(configuration: Configuration, inputFolder: Path, output: String, job_name: String) :Unit = {
    logger.info("Execution of Map Reduce Task 2 | start")
    val outputFolder2 = new Path(output + "2")
    val job2 = new Job(configuration, job_name)
    job2.setInputFormatClass(classOf[XmlInputFormatWithMultipleTags])
    job2.setJarByClass(classOf[Functionality_Impl])
    job2.setMapperClass(classOf[TaskMapper2])
    job2.setReducerClass(classOf[TaskReducer2])
    job2.setOutputKeyClass(classOf[Text])
    job2.setOutputValueClass(classOf[IntWritable])
    FileInputFormat.addInputPath(job2, inputFolder)
    FileOutputFormat.setOutputPath(job2, outputFolder2)
    job2.waitForCompletion(true)
    logger.info("Execution of Map Reduce Task 2 | end")
  }


  /**
   * Executes task 3
   * @param configuration
   * @param inputFolder
   * @param output
   * @param job_name
   */
  def mapReduceTask3(configuration: Configuration, inputFolder: Path, output: String, job_name: String) :Unit = {
    logger.info("Execution of Map Reduce Task 3 | start")
    val outputFolder3 = new Path(output + "3")
    val job3 = new Job(configuration, job_name)
    job3.setInputFormatClass(classOf[XmlInputFormatWithMultipleTags])
    job3.setJarByClass(classOf[Functionality_Impl])
    job3.setMapperClass(classOf[TaskMapper3])
//    reducer not necessary
//    job3.setReducerClass(classOf[TaskReducer3])
    job3.setOutputKeyClass(classOf[Text])
    job3.setOutputValueClass(classOf[Text])
    FileInputFormat.addInputPath(job3, inputFolder)
    FileOutputFormat.setOutputPath(job3, outputFolder3)
    job3.waitForCompletion(true)
    logger.info("Execution of Map Reduce Task 3 | end")
  }

  /**
   * Executes task 1
   * @param configuration
   * @param inputFolder
   * @param output
   * @param job_name
   */
  def mapReduceTask4(configuration: Configuration, inputFolder: Path, output: String, job_name: String) :Unit = {
    logger.info("Execution of Map Reduce Task 4 | start")
    val outputFolder4 = new Path(output + "4")
    val job4 = new Job(configuration, job_name)
    job4.setInputFormatClass(classOf[XmlInputFormatWithMultipleTags])
    job4.setJarByClass(classOf[Functionality_Impl])
    job4.setMapperClass(classOf[TaskMapper4])
    job4.setReducerClass(classOf[TaskReducer4])
    job4.setOutputKeyClass(classOf[Text])
    job4.setOutputValueClass(classOf[Text])
    FileInputFormat.addInputPath(job4, inputFolder)
    FileOutputFormat.setOutputPath(job4, outputFolder4)
    job4.waitForCompletion(true)
    logger.info("Execution of Map Reduce Task 4 | end")
  }


  /**
   * Executes task 1
   * @param configuration
   * @param inputFolder
   * @param output
   * @param job_name
   */
  def mapReduceTask5(configuration: Configuration, inputFolder: Path, output: String, job_name: String) :Unit = {
    logger.info("Execution of Map Reduce Task 5 | start")
    val outputFolder5 = new Path(output + "5")
    val job5 = new Job(configuration, job_name)
    job5.setInputFormatClass(classOf[XmlInputFormatWithMultipleTags])
    job5.setJarByClass(classOf[Functionality_Impl])
    job5.setMapperClass(classOf[TaskMapper5])
    job5.setReducerClass(classOf[TaskReducer5])
    job5.setOutputKeyClass(classOf[Text])
    job5.setOutputValueClass(classOf[Text])
    FileInputFormat.addInputPath(job5, inputFolder)
    FileOutputFormat.setOutputPath(job5, outputFolder5)
    job5.waitForCompletion(true)
    logger.info("Execution of Map Reduce Task 5 | end")
  }


  /**
   * Executes task 1
   * @param configuration
   * @param inputFolder
   * @param output
   * @param job_name
   */
  def mapReduceTask6(configuration: Configuration, inputFolder: Path, output: String, job_name: String) :Unit = {
    logger.info("Execution of Map Reduce Task 6 | start")
    val outputFolder6 = new Path(output + "6")
    val job6 = new Job(configuration, job_name)
    job6.setInputFormatClass(classOf[XmlInputFormatWithMultipleTags])
    job6.setJarByClass(classOf[Functionality_Impl])
    job6.setMapperClass(classOf[TaskMapper6])
    job6.setReducerClass(classOf[TaskReducer6])
    job6.setOutputKeyClass(classOf[Text])
    job6.setOutputValueClass(classOf[IntWritable])
    FileInputFormat.addInputPath(job6, inputFolder)
    FileOutputFormat.setOutputPath(job6, outputFolder6)
    job6.waitForCompletion(true)
    logger.info("Execution of Map Reduce Task 6 | end")
  }

}

