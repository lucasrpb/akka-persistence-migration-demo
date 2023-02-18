package demo

import akka.persistence.cassandra.SessionProvider
import com.datastax.driver.core.{Cluster, Session}

import scala.concurrent.{ExecutionContext, Future}

class MySessionProvider() extends SessionProvider {

  override def connect()(implicit ec: ExecutionContext): Future[Session] = {

    val cluster = Cluster.builder.addContactPoint("scalable-services.cassandra.cosmos.azure.com")
      .withPort(10350)
      .withCredentials("scalable-services",
        "wisskYCzdY2H10AoxLavB9w3VPSpiewpber1xfNtUSNlXpyhOM7XqvUUX0ocL7US8CF2i8DnlcCyACDbK6Mg8A==")
      .withSSL()
      .build()

    val session = cluster.connect()

    Future.successful(session)
  }
}
