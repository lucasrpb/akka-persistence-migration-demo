package demo

import akka.persistence.cassandra.query.scaladsl.CassandraReadJournal
import akka.persistence.query.{Offset, PersistenceQuery}
import akka.stream.SystemMaterializer
import akka.stream.scaladsl.Sink
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object EventsDemo {

  def main(args: Array[String]): Unit = {

    import akka.persistence.cassandra.reconciler.Reconciliation
    import akka.actor.ActorSystem
    import scala.concurrent.Future
    import akka.Done

    // System should have the same Cassandra plugin configuration as your application
    // but be careful to remove seed nodes so this doesn't join the cluster
    val system = ActorSystem("events", ConfigFactory.load("application.conf"))
    implicit val mat = SystemMaterializer(system).materializer

    val queries = PersistenceQuery(system).readJournalFor[CassandraReadJournal](CassandraReadJournal.Identifier)
    //queries.eventsByTag("counter-actor", Offset.noOffset)

    queries.eventsByPersistenceId("counter-actor", 1, 10)
      .map { m =>
        println(Console.GREEN_B, m, Console.RESET)
        m
      }
      .runWith(Sink.ignore)

    Await.ready(system.whenTerminated, Duration.Inf)

  }

}
