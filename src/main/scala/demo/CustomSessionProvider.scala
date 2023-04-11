package demo

import akka.stream.alpakka.cassandra.CqlSessionProvider
import com.datastax.oss.driver.api.core.CqlSession

import java.net.InetSocketAddress
import javax.net.ssl.SSLContext
import scala.concurrent.{ExecutionContext, Future}

class CustomSessionProvider extends CqlSessionProvider {
  override def connect()(implicit ec: ExecutionContext): Future[CqlSession] = {

    val ctx = SSLContext.getDefault()

    /*val session = CqlSession.builder()
      .addContactPoint(new InetSocketAddress("scalable-services.cassandra.cosmos.azure.com", 10350))
      .withAuthCredentials("scalable-services",
        "3wDvFHflBvltSikGAzAvTRdgc7fGYREoEqSeTUV9dW1c9iizH8ySrkNmiK8bEzEI0FflipHZvCeJACDb4kCBcQ==")
      .withSslContext(ctx)
      .withLocalDatacenter("West US")
      .build()*/

    /*val session = CqlSession.builder()
      .addContactPoint(new InetSocketAddress("ultron-cassandradb.cassandra.cosmos.azure.com", 10350))
      .withAuthCredentials("ultron-cassandradb",
        "u6JnZKCV1lDCSXR4OaUp763HRzbZOCIZcFd7GxuAmU6TsdMi3Ytx9NMCZK3r9bXGZ6suh42FkBAZry8OGxSIFg==")
      .withSslContext(ctx)
      .withLocalDatacenter("West US")
      .build()*/

    /*val session = CqlSession.builder()
      .addContactPoint(new InetSocketAddress("localhost", 10350))
      .withAuthCredentials("demo",
        "C2y6yDjf5/R+ob0N8A7Cgv30VRDJIWEHLM+4QDU5DE2nQ9nDuVTqobD4b8mGGyPMbIZnqyMsEcaGQy67XIw/Jw==")
      .withSslContext(ctx)
      .withLocalDatacenter("South Central US")
      .build()*/

    val session = CqlSession.builder()
      .addContactPoint(new InetSocketAddress("localhost", 9042))
      .withAuthCredentials("cassandra", "cassandra")
      //.withSslContext(ctx)
      //.withLocalDatacenter("South Central US")
      .withLocalDatacenter("datacenter1")
      .build()

    println("\n\nusing custom session provider...\n\n")

    /*val session = CqlSession.builder()
      //.addContactPoint(new InetSocketAddress("localhost", 9042))
      //.withAuthCredentials("scalable-services",
        //"wisskYCzdY2H10AoxLavB9w3VPSpiewpber1xfNtUSNlXpyhOM7XqvUUX0ocL7US8CF2i8DnlcCyACDbK6Mg8A==")
      //.withSslContext(ctx)
      //.withLocalDatacenter("West US")
      //.withLocalDatacenter("")
      .build()*/

    Future.successful(session)
  }
}
