package demo

/*import akka.actor.ActorSystem
import akka.stream.alpakka.cassandra.CassandraSessionSettings
import akka.stream.alpakka.cassandra.scaladsl.CassandraSessionRegistry
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object AkkaDemo {

  def main(args: Array[String]): Unit = {

    val system = ActorSystem("Demo", ConfigFactory.load("alpakka.conf"))
    val cs = CassandraSessionRegistry.get(system)

    val session = Await.result(cs.sessionFor(CassandraSessionSettings()).underlying(), Duration.Inf)

    Await.result(system.whenTerminated, Duration.Inf)

    /*val cluster = Cluster.builder.addContactPoint("ultron-cassandradb.cassandra.cosmos.azure.com")
      .withPort(10350)
      .withCredentials("ultron-cassandradb",
        "u6JnZKCV1lDCSXR4OaUp763HRzbZOCIZcFd7GxuAmU6TsdMi3Ytx9NMCZK3r9bXGZ6suh42FkBAZry8OGxSIFg==")
      .withSSL()
      .build()

    val session = cluster.connect()

    cluster.close()*/
  }

}*/
