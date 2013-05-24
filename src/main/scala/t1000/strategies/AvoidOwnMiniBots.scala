package t1000.strategies

import t1000.game.Cell.Content

object AvoidOwnMiniBots extends MaximizeEnergy {
  def energyFunction = {
    case (_, Content.Slave, 1) => -40
    case (_, Content.Slave, 2) => -30
    case (_, Content.Slave, 3) => -20
    case (_, Content.Slave, 4) => -10
    case (_, Content.Slave, _) => -5
  }
}
