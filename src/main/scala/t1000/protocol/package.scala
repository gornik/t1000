package t1000

import t1000.game.Cell.Content._
import t1000.geometry.{Vector, XY}
import t1000.game.{Cell, View}

package object protocol {
  implicit def xyToString(xy: XY): String = xy.x + ":" + xy.y

  implicit def vectorFromString(s: String): Vector = {
    if (s.isEmpty)
      return Vector(0, 0)
    val pos = s.split(":")
    Vector(pos(0).toInt, pos(1).toInt)
  }

  implicit def stringToView(s: String): View =
    new View(for {c <- s} yield charToCellContent(c))

  private def charToCellContent(c: Char): Cell.Content = c match {
    case '?' => NotVisible
    case '_' => Empty
    case 'W' => Wall
    case 'M' => Master
    case 'm' => Enemy
    case 'S' => Slave
    case 's' => EnemySlave
    case 'P' => GoodPlant
    case 'p' => BadPlant
    case 'B' => GoodAnimal
    case 'b' => BadAnimal
  }
}
