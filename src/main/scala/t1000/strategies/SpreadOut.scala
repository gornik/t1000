package t1000.strategies

import t1000.game.Cell.Content

object SpreadOut extends MaximizeEnergy {
  def energyFunction = {
    case (_, Content.Slave, 1) => -10
    case (_, Content.Slave, 2) => -9
    case (_, Content.Slave, 3) => -8
    case (_, Content.Slave, 4) => -7
    case (_, Content.Slave, 5) => -6
    case (_, Content.Slave, 6) => -5
    case (_, Content.Slave, 7) => -4
    case (_, Content.Slave, 8) => -3
    case (_, Content.Slave, 9) => -2
    case (_, Content.Slave, _) => -1
  }
}
