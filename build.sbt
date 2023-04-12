version in ThisBuild := "0.1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "akka-persistence-migration-demo"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-persistence" % "2.6.9",
  "com.typesafe.akka" %% "akka-persistence-cassandra" % "1.1.0"
)

dependencyOverrides ++= Seq(
  "com.datastax.oss" % "java-driver-core" % "4.15.0"
)
