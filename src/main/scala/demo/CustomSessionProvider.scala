/*package demo

import akka.stream.alpakka.cassandra.CqlSessionProvider
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.config.DriverConfigLoader
import com.datastax.oss.driver.api.core.metadata.EndPoint
import com.datastax.oss.driver.api.core.ssl.SslEngineFactory
import com.datastax.oss.driver.internal.core.ssl.{DefaultSslEngineFactory, JdkSslHandlerFactory, SniSslEngineFactory}
import sun.security.ssl.SSLContextImpl.DefaultSSLContext

import java.net.InetSocketAddress
import java.util.concurrent.Executor
import javax.net.ssl.{SSLContext, SSLEngine}
import scala.concurrent.{ExecutionContext, Future, Promise}

class CustomSessionProvider extends CqlSessionProvider {
  override def connect()(implicit ec: ExecutionContext): Future[CqlSession] = {
    /*val cluster = Cluster.builder.addContactPoint("ultron-cassandradb.cassandra.cosmos.azure.com")
      .withPort(10350)
      .withCredentials("ultron-cassandradb",
        "u6JnZKCV1lDCSXR4OaUp763HRzbZOCIZcFd7GxuAmU6TsdMi3Ytx9NMCZK3r9bXGZ6suh42FkBAZry8OGxSIFg==")
      .withSSL()
      .build()

    val session = cluster.connect()*/

   // val factory = new DefaultSslEngineFactory()

//    rctx.newSSLHandler(null)

    val ctx = SSLContext.getDefault

    val cqlSession = CqlSession.builder()
      .addContactPoint(new InetSocketAddress("scalable-services.cassandra.cosmos.azure.com", 10350))
      .withAuthCredentials("scalable-services",
        "jZ3owe27HZmNhB8FACsjplufPYQJXN71P0tin2hegSWRjTrQpACKc85OStqzJYTwE2VjBLHxu8T8ACDbvwZZXw==")
      //.withLocalDatacenter("West US")
      .withLocalDatacenter("West US")
      //.withLocalDatacenter("")
      .withSslContext(ctx)
      .build()

   // session.init()

    Future.successful(cqlSession)
  }
}
*/
