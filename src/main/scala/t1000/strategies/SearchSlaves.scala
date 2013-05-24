package t1000.strategies

import t1000.game.Cell.Content.Slave

object SearchSlaves extends MaximizeEnergy {
  override def energyFunction = {
    case (_, Slave, 1) => 1000
    case (_, Slave, 2) => 500
    case (_, Slave, 3) => 400
    case (_, Slave, 4) => 300
    case (_, Slave, 5) => 200
    case (_, Slave, 6) => 100
    case (_, Slave, _) => 50
  }
}
