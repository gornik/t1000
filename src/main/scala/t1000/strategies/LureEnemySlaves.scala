package t1000.strategies

import t1000.game.Cell.Content.EnemySlave

object LureEnemySlaves extends MaximizeEnergy {
  def energyFunction = {
    case (_, EnemySlave, 1) => 100
    case (_, EnemySlave, 2) => 90
    case (_, EnemySlave, 3) => 80
    case (_, EnemySlave, 4) => 40
    case (_, EnemySlave, 5) => 30
    case (_, EnemySlave, _) => 10
  }
}
