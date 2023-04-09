package demo

import akka.persistence.cassandra.SessionProvider
import com.datastax.driver.core.{Cluster, Session}

import scala.concurrent.{ExecutionContext, Future}

class MySessionProvider() extends SessionProvider {

  override def connect()(implicit ec: ExecutionContext): Future[Session] = {

    val cluster = Cluster.builder.addContactPoint("localhost")
      .withPort(10350)
      .withCredentials("local",
        "C2y6yDjf5/R+ob0N8A7Cgv30VRDJIWEHLM+4QDU5DE2nQ9nDuVTqobD4b8mGGyPMbIZnqyMsEcaGQy67XIw/Jw==")
      .withSSL()
      .build()

    val session = cluster.connect()

    Future.successful(session)
  }
}
