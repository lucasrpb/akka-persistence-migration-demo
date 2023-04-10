package demo

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import demo.models.{Checkpoint, Command, Decrement, Increment}
import demo.persistence.CounterPersistentActor

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {

  val system = ActorSystem("PersistenceActor", ConfigFactory.load("application.conf"))

  val counterPersistentActor = system.actorOf(CounterPersistentActor.props("counter-actor"),
    "CounterPersistentActor")

  counterPersistentActor ! Command(Increment(3))
  counterPersistentActor ! Command(Increment(3))
  counterPersistentActor ! Checkpoint

 // Thread.sleep(1000)

 // system.terminate()

 // akka.persistence.cassandra.ConfigSessionProvider

  //system.terminate()

  Await.result(system.whenTerminated, Duration.Inf)
}