package demo.persistence

import akka.actor.Props
import akka.persistence.journal.Tagged
import akka.persistence.{PersistentActor, SnapshotOffer}
import demo.models._
import demo.persistence.CounterPersistentActor.Response

class CounterPersistentActor(id: String, tag: Option[String] = None) extends PersistentActor {

  override val persistenceId: String = id
  var state = State(count = 0)

  def updateState(event:Event) {
    event match {
      case Event(Increment(count)) => state = State(state.count + count)
      case Event(Decrement(count)) => state = State(state.count - count)
    }
  }

  override def receiveRecover: Receive = {
    case event: Event =>
      println(s"\n${Console.GREEN}${Console.BOLD}Actor is currently recovering its state${Console.RESET}\n")
      updateState(event)
    case SnapshotOffer(_, snapshot: State) =>
      println(s"Snapshot data: $snapshot")
      state = snapshot
  }

  override def receiveCommand: Receive = {
    case command @ Command(op) =>
      println(s"\n${Console.RED}${Console.BOLD}$command is under process${Console.RESET}\n")
      if(tag.isEmpty) {
        persist(Event(op)) { event =>
          updateState(event)
          sender() ! Response("Done Processing")
        }
      } else {
        persist(Tagged(Event(op), Set("tag2"))) { event =>
          updateState(event.payload.asInstanceOf[Event])
          sender() ! Response("Done Processing")
        }
      }
    case Checkpoint =>
      println(s"\n${Console.MAGENTA}${Console.BOLD}Current State: ${state.count}${Console.RESET}\n")
      sender() ! Response(s"Current State: ${state.count}")
  }

}

object CounterPersistentActor {

  def props(id: String) = Props(new CounterPersistentActor(id))

  case class Response(message: String)

}
