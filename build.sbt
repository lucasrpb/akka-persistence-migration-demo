version in ThisBuild := "0.1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.12.4"

resolvers += "dnvriend" at "http://dl.bintray.com/dnvriend/maven"

lazy val root = (project in file("."))
  .settings(
    name := "akka-persistence-migration-demo"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-persistence" % "2.5.19",
  "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.98",
  "com.github.dnvriend" %% "akka-persistence-inmemory" % "2.5.15.2"
)
