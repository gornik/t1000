package t1000.geometry

import t1000.geometry.Coordinates.Absolute

trait Coordinates {
  val zero: Coordinates.Absolute.Point

  case class Point(x: Int, y: Int) extends XY {
    val coordinates = capturedInstance

    def translateTo(other: Coordinates): other.Point =
      other.Point(x + zero.x - other.zero.x, y + zero.y - other.zero.y)

    def absolute = translateTo(Coordinates.Absolute)

    def toVector = Vector(x, y)

    def neighbours = for {
      neighbourX <- x - 1 to x + 1
      neighbourY <- y - 1 to y + 1
      if neighbourX != x || neighbourY != y
    } yield Point(neighbourX, neighbourY)
  }

  private val capturedInstance = this
}

object Coordinates {

  object Absolute extends Coordinates {
    val zero = Absolute.Point(0, 0)
  }

  def relativeFrom(point: Absolute.Point) = new Coordinates {
    val zero = point
  }
}