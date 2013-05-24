package t1000.strategies

import t1000.game.Cell.Content.{BadPlant, Wall}

object AvoidObstacles extends MaximizeEnergy {
  override def energyFunction = {
    case (_, Wall, 1) => -10000
    case (_, BadPlant, 0) => -100
  }
}
