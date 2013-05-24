package t1000.strategies

import t1000.game.Cell.Content

object AvoidOtherBots extends MaximizeEnergy {
  def energyFunction = {
    case (_, Content.Enemy, 1) => -1000
    case (_, Content.Enemy, 2) => -50
    case (_, Content.Enemy, 3) => -40
    case (_, Content.Enemy, 4) => -30
    case (_, Content.Enemy, 5) => -20
    case (_, Content.Enemy, 6) => -15
    case (_, Content.Enemy, 7) => -10
    case (_, Content.Enemy, _) => -5
    case (_, Content.EnemySlave, 1) => -1000
    case (_, Content.EnemySlave, 2) => -50
    case (_, Content.EnemySlave, 3) => -40
    case (_, Content.EnemySlave, 4) => -30
    case (_, Content.EnemySlave, 5) => -20
    case (_, Content.EnemySlave, 6) => -15
    case (_, Content.EnemySlave, 7) => -10
    case (_, Content.EnemySlave, _) => -5
  }
}


