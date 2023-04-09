package demo

import com.datastax.oss.driver.api.core.CqlSession

import java.net.InetSocketAddress

object DeleteKeyspaces {

  def main(args: Array[String]): Unit = {

    val session = CqlSession.builder()
      .addContactPoint(new InetSocketAddress("localhost", 9042))
      .withAuthCredentials("cassandra", "cassandra")
      //.withSslContext(ctx)
      //.withLocalDatacenter("South Central US")
      .build()

    println("akka keyspace drop: ",session.execute("drop keyspace if exists akka;").wasApplied())
    println("akka_snapshot keyspace drop: ",session.execute("drop keyspace if exists akka_snapshot;").wasApplied())

    session.close()
  }

}
