package demo

import akka.persistence.cassandra.SessionProvider
import com.datastax.driver.core.{Cluster, Session}

import scala.concurrent.{ExecutionContext, Future}

class MySessionProvider() extends SessionProvider {

  override def connect()(implicit ec: ExecutionContext): Future[Session] = {

    val cluster = Cluster.builder.addContactPoint("scalable-services.cassandra.cosmos.azure.com")
      .withPort(10350)
      .withCredentials("scalable-services",
        "3wDvFHflBvltSikGAzAvTRdgc7fGYREoEqSeTUV9dW1c9iizH8ySrkNmiK8bEzEI0FflipHZvCeJACDb4kCBcQ==")
      .withSSL()
      .build()

    val session = cluster.connect()

    Future.successful(session)
  }
}
