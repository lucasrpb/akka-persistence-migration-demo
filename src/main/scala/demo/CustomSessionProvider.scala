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
        "wisskYCzdY2H10AoxLavB9w3VPSpiewpber1xfNtUSNlXpyhOM7XqvUUX0ocL7US8CF2i8DnlcCyACDbK6Mg8A==")
      .withSslContext(ctx)
      .withLocalDatacenter("West US")
      .build()

    println("\n\nhere...\n\n")*/

    val session = CqlSession.builder()
      //.addContactPoint(new InetSocketAddress("localhost", 9042))
      //.withAuthCredentials("scalable-services",
        //"wisskYCzdY2H10AoxLavB9w3VPSpiewpber1xfNtUSNlXpyhOM7XqvUUX0ocL7US8CF2i8DnlcCyACDbK6Mg8A==")
      //.withSslContext(ctx)
      //.withLocalDatacenter("West US")
      //.withLocalDatacenter("")
      .build()

    Future.successful(session)
  }
}
