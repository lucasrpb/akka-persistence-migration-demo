package demo.persistence.query

import akka.NotUsed
import akka.actor.ActorSystem
import akka.persistence.query.{EventEnvelope, PersistenceQuery, Sequence}
import akka.persistence.query.journal.leveldb.scaladsl.LeveldbReadJournal
import akka.stream.{ActorMaterializer, SystemMaterializer}
import akka.stream.scaladsl.Source

object EventsByTag /*extends App*/ {

  val system = ActorSystem("PersistentQuery")
  val queries = PersistenceQuery(system).readJournalFor[LeveldbReadJournal](LeveldbReadJournal.Identifier)

  implicit val mat = SystemMaterializer(system).materializer

  val eventsByTagSource: Source[EventEnvelope, NotUsed] = queries.eventsByTag(tag = "tag2", offset = Sequence(0L))

  eventsByTagSource.runForeach { event =>
    println(event)
  }

}
