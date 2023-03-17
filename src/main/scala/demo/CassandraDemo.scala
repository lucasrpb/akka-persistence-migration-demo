package demo

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.ssl.SslEngineFactory

import java.net.InetSocketAddress
import javax.net.ssl.SSLContext

object CassandraDemo {

  def main(args: Array[String]): Unit = {

    val ctx = SSLContext.getDefault()

    val session = CqlSession.builder()
      .addContactPoint(new InetSocketAddress("localhost", 10350))
      .withAuthCredentials("demo",
        "C2y6yDjf5/R+ob0N8A7Cgv30VRDJIWEHLM+4QDU5DE2nQ9nDuVTqobD4b8mGGyPMbIZnqyMsEcaGQy67XIw/Jw==")
      .withSslContext(ctx)
      .withLocalDatacenter("South Central US")
      .withKeyspace("akka_snapshot_migration")
      .build()

    //val rs = session.execute(s"select * from akka_migration.messages limit 100 allow filtering;")
    /*val rs = session.execute(
      s"""
         |CREATE KEYSPACE IF NOT EXISTS akka_snapshot_migration
         |WITH REPLICATION = { 'class' : 'SimpleStrategy','replication_factor':1 };""".stripMargin)*/

    /*val rs = session.execute(
      s"""
         |INSERT INTO test(id, email) values ('2', 'lucasrpb2@gmail.com');""".stripMargin)

    println()*/

    val rs = session.execute(
      s"""SELECT * FROM test allow filtering;""".stripMargin)

    val it = rs.iterator()

    while(it.hasNext){
      val row = it.next()
      println(row.getString("email"))
    }

    session.close()

  }

}
