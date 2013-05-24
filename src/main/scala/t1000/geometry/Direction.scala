package t1000.geometry

import t1000.game.BasicActions
import BasicActions.Move

sealed trait Direction extends XY {
  def toVector = Vector(x, y)
  def negative = toVector.negative.direction
  def move = Move(this)
  val adjacent: Seq[Direction]
}

object Direction {
  case object East extends Direction {
    val x = 1;  val y = 0
    val adjacent: Seq[Direction] = Seq(NorthEast, SouthEast)
  }
  case object NorthEast extends Direction {
    val x = 1;  val y = -1
    val adjacent: Seq[Direction] = Seq(North, East)
  }
  case object North extends Direction {
    val x = 0;  val y = -1
    val adjacent: Seq[Direction] = Seq(NorthEast, NorthWest)
  }
  case object NorthWest extends Direction {
    val x = -1; val y = -1
    val adjacent: Seq[Direction] = Seq(North, West)
  }
  case object West extends Direction {
    val x = -1; val y = 0
    val adjacent: Seq[Direction] = Seq(NorthWest, SouthWest)
  }
  case object SouthWest extends Direction {
    val x = -1; val y = 1
    val adjacent: Seq[Direction] = Seq(South, West)
  }
  case object South extends Direction {
    val x = 0;  val y = 1
    val adjacent: Seq[Direction] = Seq(SouthWest, SouthEast)
  }
  case object SouthEast extends Direction {
    val x = 1;  val y = 1
    val adjacent: Seq[Direction] = Seq(South, East)
  }
  case object None extends Direction {
    val x = 0;  val y = 0
    val adjacent: Seq[Direction] = Seq()
  }

  val all = Seq(
    East, NorthEast, North, NorthWest, West, SouthWest, South, SouthEast
  )
}