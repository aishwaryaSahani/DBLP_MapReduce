name := "aishwarya_sahani_hw2"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.0",
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "org.slf4j" % "slf4j-log4j12" % "1.7.30",
  "com.novocode" % "junit-interface" % "0.8" % "test->default",
  "org.scala-lang.modules" %% "scala-xml" % "1.3.0",
  "org.apache.hadoop" % "hadoop-client" % "3.2.1",
  "org.scala-lang" % "scala-library" % "2.12.6"
)
