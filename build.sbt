organization := "name.dmitrym"

name := "MacroDI"

version := "SNAPSHOT"

scalaVersion := "2.10.3"

scalacOptions ++= Seq(
    "-language:experimental.macros"
  )

scalacOptions in Test ++= Seq(
    "-Yrangepos"
  )

libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % "2.10.3",
    "com.typesafe" % "config" % "1.2.0",
    "org.specs2" %% "specs2" % "2.3.7" % "test"
  )
