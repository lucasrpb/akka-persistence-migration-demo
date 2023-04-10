package demo

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.BatchStatement
import com.datastax.oss.driver.api.core.cql.BatchType

import java.net.InetSocketAddress
import javax.net.ssl.SSLContext

object CassandraDemo {

  def main(args: Array[String]): Unit = {

    val ctx = SSLContext.getDefault()

    val session = CqlSession.builder()
      .addContactPoint(new InetSocketAddress("localhost", 10350))
      .withAuthCredentials("local",
        "C2y6yDjf5/R+ob0N8A7Cgv30VRDJIWEHLM+4QDU5DE2nQ9nDuVTqobD4b8mGGyPMbIZnqyMsEcaGQy67XIw/Jw==")
      .withSslContext(ctx)
      .withLocalDatacenter("South Central US")
     // .withKeyspace("akka_snapshot_migration")
      .build()

    /*val rs = session.execute(
      s"""SELECT * FROM test allow filtering;""".stripMargin)

    val it = rs.iterator()

    while(it.hasNext){
      val row = it.next()
      println(row.getString("email"))
    }*/

    session.execute("""CREATE KEYSPACE IF NOT EXISTS demo
                      |  WITH REPLICATION = {
                      |   'class' : 'SimpleStrategy',
                      |   'replication_factor' : 1
                      |  };""".stripMargin)

    session.execute(
      """CREATE TABLE IF NOT EXISTS demo.users(id text, email text, PRIMARY KEY(id));""".stripMargin)

    val batch = BatchStatement.builder(BatchType.LOGGED)

    batch.addStatement(session.prepare("insert into demo.users(id, email) values(?,?)")
      .bind("lucasrpb", "lucasrpb@gmail.com"))

    batch.addStatement(session.prepare("insert into demo.users(id, email) values(?,?)")
      .bind("ivone", "ivone@gmail.com"))

    session.execute(batch.build())

    session.close()

  }

}
