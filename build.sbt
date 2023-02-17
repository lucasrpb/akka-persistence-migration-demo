version in ThisBuild := "0.1.0"

scalaVersion in ThisBuild := "2.12.4"

resolvers += "dnvriend" at "http://dl.bintray.com/dnvriend/maven"

lazy val root = (project in file("."))
  .settings(
    name := "akka-persistence-migration-demo"
  )

val akkaVersion = "2.5.23"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-persistence" % "2.5.23",
  "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.98",
  "com.datastax.cassandra" % "cassandra-driver-core" % "3.7.2",
  "com.github.dnvriend" %% "akka-persistence-inmemory" % "2.5.15.2"

  /*"com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.98",
  "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-sharding-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-protobuf" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-kafka" % "2.1.1",
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "com.datastax.cassandra" % "cassandra-driver-core" % "3.7.2",
  "com.lightbend.akka" %% "akka-stream-alpakka-cassandra" % "1.0.2"*/
)
