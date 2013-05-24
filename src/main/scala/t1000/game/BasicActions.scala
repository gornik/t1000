package t1000.game

import t1000.geometry.{Vector, Direction}

sealed trait Action

sealed trait BasicAction extends Action

object BasicActions {

  case object NoAction extends BasicAction

  case class Move(direction: Direction) extends BasicAction

  case class Spawn(direction: Direction, name: String, energy: Int,
                   additional: (String, String)*) extends BasicAction

  case class Set(params: (String, String)*) extends BasicAction

  case class Explode(size: Int) extends BasicAction

  case class Say(text: String) extends BasicAction

  case class Status(text: String) extends BasicAction

  case class MarkCell(position: Vector) extends BasicAction

  case class DrawLine(from: Vector, to: Vector, color: String)
    extends BasicAction

  case class Log(text: String) extends BasicAction

}
