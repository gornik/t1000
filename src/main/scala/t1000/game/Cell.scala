package t1000.game

import t1000.geometry.Coordinates

case class Cell(position: Coordinates#Point, content: Cell.Content) {
  def translateCoordinates(target: Coordinates) = position.translateTo(target)
}


object Cell {

  sealed trait Content {
    def isObstacle = false
    def isFood = false
  }

  object Content {

    case object NotVisible extends Content { override def toString = "?"}

    case object Empty extends Content { override def toString = "_"}

    case object Wall extends Content {
      override def isObstacle = true
      override def toString = "W"
    }

    case object Master extends Content { override def toString = "M"}

    case object Enemy extends Content { override def toString = "m"}

    case object Slave extends Content { override def toString = "S"}

    case object EnemySlave extends Content { override def toString = "s"}

    case object GoodPlant extends Content {
      override def isFood = true
      override def toString = "P"
    }

    case object BadPlant extends Content {
      override def isObstacle = true
      override def toString = "p"
    }

    case object GoodAnimal extends Content{
      override def isFood = true
      override def toString = "B"
    }

    case object BadAnimal extends Content { override def toString = "b"}

  }

}