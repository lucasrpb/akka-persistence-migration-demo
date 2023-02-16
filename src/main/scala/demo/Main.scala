package demo

import akka.actor.{ActorSystem, Props}
import demo.models.{Checkpoint, Command, Decrement, Increment}
import demo.persistence.CounterPersistentActor

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {

  val system = ActorSystem("PersistenceActor")

  val counterPersistentActor = system.actorOf(CounterPersistentActor.props("counter-actor"),
    "CounterPersistentActor")

  counterPersistentActor ! Command(Increment(3))
  //counterPersistentActor ! Command(Increment(4))
  //counterPersistentActor ! Command(Decrement(2))
  counterPersistentActor ! Checkpoint

 // Thread.sleep(1000)

 // system.terminate()

  Await.result(system.whenTerminated, Duration.Inf)
}