package demo

import akka.stream.alpakka.cassandra.CqlSessionProvider
import com.datastax.oss.driver.api.core.CqlSession

import java.net.InetSocketAddress
import scala.concurrent.{ExecutionContext, Future}

class MySessionProvider() extends CqlSessionProvider {

  override def connect()(implicit ec: ExecutionContext): Future[CqlSession] = {

    val session = CqlSession.builder()
      .addContactPoint(new InetSocketAddress("localhost", 9042))
      .withAuthCredentials("cassandra", "cassandra")
      //.withSslContext(ctx)
      //.withLocalDatacenter("South Central US")
      .withLocalDatacenter("datacenter1")
      .build()

    Future.successful(session)
  }
}
