package t1000.game

import t1000.geometry.{Coordinates, Square}
import t1000.{geometry, game}

class View(content: Seq[Cell.Content]) {
  val values = new Square[Cell.Content](content)
  def cellCount = content.size

  def nearest(targets: Cell.Content*) = {
    val found = BotsPerspective.cells
      .filter(cell => targets.contains(cell.content))
    if (found.isEmpty) None
    else Some(found
      .map(cell => cell.position.toVector)
      .minBy(_.distance))
  }

  def neighbours(point: Coordinates#Point) = {
    for {n <- point.absolute.neighbours; if values.contains(n)}
    yield game.Cell(n.translateTo(point.coordinates), values.contentAt(n).get)
  }

  override def toString = values.toString

  object AbsolutePerspective extends View.Perspective {
    val coordinateSystem = Coordinates.Absolute
    val cells = for {
      row<- 0 until values.size;
      col <- 0 until values.size
      p = Coordinates.Absolute.Point(row, col)
    } yield game.Cell(p, values.contentAt(p).get)

    def cellAt(vector: geometry.Vector) = {
      val abs = vector.asAbsolutePoint
      game.Cell(abs, values.contentAt(abs).get)
    }
  }

  object BotsPerspective extends View.Perspective {
    val coordinateSystem = Coordinates.relativeFrom(values.center)
    val cells = AbsolutePerspective.cells.map(cell =>
      game.Cell(cell.position.translateTo(coordinateSystem), cell.content))

    def cellAt(vector: geometry.Vector) =
      AbsolutePerspective.cellAt(
        vector.asPointIn(coordinateSystem).absolute.toVector)
  }

}


object View {

  trait Perspective {
    val coordinateSystem: Coordinates
    val cells: Seq[game.Cell]

    def cellAt(vector: geometry.Vector): game.Cell
  }

}