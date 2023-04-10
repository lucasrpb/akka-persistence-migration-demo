package demo

import scala.util.Failure
import scala.util.Success
import akka.actor.ActorSystem
import akka.persistence.cassandra.query.scaladsl.CassandraReadJournal
import akka.persistence.cassandra.reconciler.Reconciliation
import akka.persistence.query.PersistenceQuery
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Reconciler {

  def main(args: Array[String]): Unit = {
    // System should have the same Cassandra plugin configuration as your application
    // but be careful to remove seed nodes so this doesn't join the cluster
    val system = ActorSystem("Reconciler", ConfigFactory.load("application.conf"))
    import system.dispatcher

    val rec = new Reconciliation(system)
    val result = rec.rebuildAllPersistenceIds()

    result.onComplete {
      case Success(_) =>
        system.log.info("All persistenceIds migrated.")
        system.terminate()
      case Failure(e) =>
        system.log.error(e, "All persistenceIds migration failed.")
        system.terminate()
    }

    Await.result(system.whenTerminated, Duration.Inf)
  }

}
