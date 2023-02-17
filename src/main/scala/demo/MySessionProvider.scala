package demo

import akka.persistence.cassandra.SessionProvider
import com.datastax.driver.core.{Cluster, Session}

import scala.concurrent.{ExecutionContext, Future}

class MySessionProvider() extends SessionProvider {

  override def connect()(implicit ec: ExecutionContext): Future[Session] = {

    val cluster = Cluster.builder.addContactPoint("ultron-cassandradb.cassandra.cosmos.azure.com")
      .withPort(10350)
      .withCredentials("ultron-cassandradb",
        "u6JnZKCV1lDCSXR4OaUp763HRzbZOCIZcFd7GxuAmU6TsdMi3Ytx9NMCZK3r9bXGZ6suh42FkBAZry8OGxSIFg==")
      .withSSL()
      .build()

    val session = cluster.connect()

    Future.successful(session)
  }
}
