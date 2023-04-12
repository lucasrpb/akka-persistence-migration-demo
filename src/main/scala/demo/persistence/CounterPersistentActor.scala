package demo.persistence

import akka.actor.typed.scaladsl._
import akka.actor.typed.{Behavior, SupervisorStrategy}
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.{Effect, EventSourcedBehavior}
import demo.models._

import java.util.concurrent.{Executors, ThreadFactory}
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration.DurationInt

object CounterPersistentActor {

  def eventHandler(state: State, event: Event): State = {
    println(Console.GREEN_B, s"recovering state...", Console.RESET)

    event match {
      case Event(increment: Increment) => State(state.count + increment.counter)
      case Event(decrement: Decrement) => State(state.count - decrement.counter)
    }
  }

  def commandHandler(context: ActorContext[Command])(implicit ec: ExecutionContext): (State, Command) => Effect[Event, State] = {
    (state, cmd) => cmd match {
      case command @ Command(op) => op match {
        case cmd: Increment => Effect.persist(Event(cmd)).thenRun { s =>
          println(Console.MAGENTA_B, s"incremented", Console.RESET)
          s
        }

        case cmd: Decrement => Effect.persist(Event(cmd)).thenRun { s =>
          println(Console.MAGENTA_B, s"decremented ${s}", Console.RESET)
          s
        }

        case Checkpoint => Effect.none.thenRun { _ =>
          println(Console.GREEN_B, s"current state: ${state}", Console.RESET)
        }

        case CreateChild2(name) =>
          Effect.none.thenRun { _ =>
            val actor = context.spawn(Behaviors.receiveMessage[String] {
              case msg =>
                println(s"echoing ${msg}...")
                Behaviors.same
            }, name)

            actor ! "hi"
          }

        case CreateChild(name) =>

          Effect.unhandled.thenRun { _ =>
            println(Console.RED_B, s"create child ${name}...", Console.RESET)

            Executors.newSingleThreadExecutor().execute(new Runnable {
              override def run(): Unit = {
                //context.self ! Command(CreateChild2(name))

                /*val actor = context.spawn(Behaviors.receiveMessage[String] {
                  case msg =>
                    println(s"echoing ${msg}...")
                    Behaviors.same
                }, name)

                actor ! "hi"*/
              }
            })

          }
      }

      case _ => Effect.none
    }
  }

  def apply(name: String): Behavior[Command] = {
    Behaviors.supervise[Command] {
      Behaviors.setup[Command] { ctx =>

        implicit val ec = ctx.executionContext

        EventSourcedBehavior[Command, Event, State](
          persistenceId = PersistenceId.ofUniqueId(name),
          emptyState = State(0),
          commandHandler = commandHandler(ctx),
          eventHandler = eventHandler)
      }
    }.onFailure[Throwable](SupervisorStrategy.restartWithBackoff(5.seconds, 20.seconds, 0.2))
  }

}