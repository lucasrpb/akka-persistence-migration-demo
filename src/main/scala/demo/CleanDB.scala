package demo

import com.datastax.oss.driver.api.core.CqlSession

import java.net.InetSocketAddress

object CleanDB {

  def main(args: Array[String]): Unit = {

  //  val secretsConfig: Config = ConfigFactory.parseFile(new File("applicationsecrets.txt"))
   // val password = secretsConfig.getString("cassandra-journal.cassandra-journal.authentication.password")

    /*val cluster = Cluster.builder.addContactPoint("ultron-cassandradb.cassandra.cosmos.azure.com")
      .withPort(10350)
      .withCredentials("ultron-cassandradb", password)
      .withSSL()
      .build()*/

    val session = CqlSession.builder()
      .addContactPoint(new InetSocketAddress("localhost", 9042))
      .withAuthCredentials("cassandra", "cassandra")
      //.withSslContext(ctx)
      //.withLocalDatacenter("South Central US")
      .build()

    val isStage = false
    val k1 = if(isStage) "akka_stage" else "akka_dev"
    val k2 = if(isStage) "akka_stage_snapshot" else "akka_dev_snapshot"

    var rs = session.execute(s"truncate table ${k1}.messages;")
    assert(rs.wasApplied())

    rs = session.execute(s"truncate table ${k1}.metadata;")
    assert(rs.wasApplied())

    rs = session.execute(s"truncate table ${k1}.tag_scanning;")
    assert(rs.wasApplied())

    rs = session.execute(s"truncate table ${k1}.tag_views;")
    assert(rs.wasApplied())

    rs = session.execute(s"truncate table ${k1}.tag_write_progress;")
    assert(rs.wasApplied())

    rs = session.execute(s"truncate table ${k2}.snapshots;")
    assert(rs.wasApplied())

    /*val rs = session.execute("alter table akka_dev.messages drop used;")
    println(rs.wasApplied())*/

    session.close()

    println("done")
  }

}

