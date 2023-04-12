package demo

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import com.typesafe.config.ConfigFactory
import demo.models.{Checkpoint, Command, CreateChild, Decrement, Increment}
import demo.persistence.CounterPersistentActor

import java.util.concurrent.Executors
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {

  val system = ActorSystem.create[Nothing](Behaviors.empty[Nothing],
    "PersistenceActor", ConfigFactory.load("application.conf"))

  val counter = system.systemActorOf(CounterPersistentActor("counter"), "CounterActor")

  //counter ! Command(Increment(3))
  counter ! Command(Checkpoint)

  Executors.newSingleThreadExecutor().execute(new Runnable {
    override def run(): Unit = {
      counter ! Command(CreateChild("2023-04-11"))
    }
  })

  Await.result(system.whenTerminated, Duration.Inf)
}