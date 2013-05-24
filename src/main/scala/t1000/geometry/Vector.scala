package t1000.geometry

import t1000.game.BasicActions
import BasicActions.Move


case class Vector(x: Int, y: Int) extends XY {

  import Direction._

  def +(other: Vector) = Vector(x + other.x, y + other.y)

  def -(other: Vector) = this + other.negative

  def distance = math.max(math.abs(x), math.abs(y))

  def negative = new Vector(-x, -y)

  def moveTowards = Move(direction)
  def moveAwayFrom = Move(negative.direction)

  def direction = (x, y) match {
    case (c, r) if (c == r && c > 0) => SouthEast
    case (c, r) if (c == r && c < 0) => NorthWest
    case (c, r) if (c == -r && c < 0) => SouthWest
    case (c, r) if (c == -r && c > 0) => NorthEast
    case (c, r) if (r < 0 && math.abs(c) < -r) => North
    case (c, r) if (r > 0 && math.abs(c) < r) => South
    case (c, r) if (c > 0 && math.abs(r) < c) => East
    case (c, r) if (c < 0 && math.abs(r) < -c) => West
    case (0, 0) => None
  }

  def asPointIn(coordinates: Coordinates) = coordinates.Point(x, y)
  def asAbsolutePoint = Coordinates.Absolute.Point(x, y)
}

