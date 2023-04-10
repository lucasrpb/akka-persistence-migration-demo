package demo

import akka.persistence.cassandra.query.scaladsl.CassandraReadJournal
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.BatchStatement
import com.datastax.oss.driver.api.core.cql.BatchType

import java.net.InetSocketAddress
import javax.net.ssl.SSLContext
import scala.concurrent.Await

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

    /*val batch = BatchStatement.builder(BatchType.LOGGED)

    batch.addStatement(session.prepare("insert into demo.users(id, email) values(?,?)")
      .bind("lucasrpb", "lucasrpb@gmail.com"))

    batch.addStatement(session.prepare("insert into demo.users(id, email) values(?,?)")
      .bind("ivone", "ivone@gmail.com"))

    session.execute(batch.build())*/

    session.execute(
      """
        |INSERT INTO akka.messages
        |(persistence_id, partition_nr, sequence_nr, "timestamp", timebucket, writer_uuid, ser_id, ser_manifest, event_manifest, event, meta_ser_id, meta_ser_manifest, meta, tags)
        |VALUES('counter-actor', 0, 8, e0c66d60-d741-11ed-bb06-d93197db634f, '1681088400000', '91aefbff-189a-4980-8255-15eb9ddcd0f2', 1, '', '', 0xC2ACC3AD20207372202064656D6F2E6D6F64656C732E4576656E74C2A3C2A2C2B620C2A72E206F2020204C20206F7065726174696F6E7420204C64656D6F2F6D6F64656C732F4F7065726174696F6E3B78707372202064656D6F2E6D6F64656C732E496E6372656D656E74C3A8C38B74C39D2020C3B7C2AD202020492020636F756E746572787020202020, NULL, NULL, NULL, NULL);
        |""".stripMargin)
    
    session.close()

  }

}
