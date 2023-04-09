package demo

import com.datastax.driver.core.Cluster

object DeleteKeyspaces {

  def main(args: Array[String]): Unit = {

    val cluster = Cluster.builder.addContactPoint("scalable-services.cassandra.cosmos.azure.com")
      .withPort(10350)
      .withCredentials("scalable-services",
        "3wDvFHflBvltSikGAzAvTRdgc7fGYREoEqSeTUV9dW1c9iizH8ySrkNmiK8bEzEI0FflipHZvCeJACDb4kCBcQ==")
      .withSSL()
      .build()

    val session = cluster.connect()

    println("akka keyspace drop: ",session.execute("drop keyspace if exists akka;").wasApplied())
    println("akka_snapshot keyspace drop: ",session.execute("drop keyspace if exists akka_snapshot;").wasApplied())

    session.close()
    cluster.close()
  }

}
