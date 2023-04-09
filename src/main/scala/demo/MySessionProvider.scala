package demo

import akka.persistence.cassandra.SessionProvider
import com.datastax.driver.core.{Cluster, Session}

import scala.concurrent.{ExecutionContext, Future}

class MySessionProvider() extends SessionProvider {

  override def connect()(implicit ec: ExecutionContext): Future[Session] = {

    val cluster = Cluster.builder.addContactPoint("localhost")
      .withPort(9042)
      .withCredentials("cassandra", "cassandra")
      //.withSSL()
      .build()

    val session = cluster.connect()

    Future.successful(session)
  }
}
