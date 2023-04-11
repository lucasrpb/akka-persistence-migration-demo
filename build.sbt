version in ThisBuild := "0.1.0"

scalaVersion in ThisBuild := "2.12.4"

lazy val root = (project in file("."))
  .settings(
    name := "akka-persistence-migration-demo"
  )

val akkaVersion = "2.5.23"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-persistence" % "2.5.32",
  "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.99"
)
