package demo.models

sealed trait Operation

@SerialVersionUID(2L)
case class Increment(counter: Int) extends Operation

@SerialVersionUID(2L)
case class Decrement(counter: Int) extends Operation

@SerialVersionUID(2L)
case object Checkpoint extends Operation