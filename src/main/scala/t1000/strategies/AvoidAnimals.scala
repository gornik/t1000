package t1000.strategies

import t1000.{BotContext}
import t1000.game.BasicActions
import BasicActions.Log
import t1000.game.Cell.Content.BadAnimal

object AvoidAnimals extends MaximizeEnergy {
  override def energyFunction = {
    case (_, BadAnimal, 1) => -150
    case (_, BadAnimal, 2) => -150
    case (_, BadAnimal, 3) => -140
    case (_, BadAnimal, 4) => -130
    case (_, BadAnimal, 5) => -120
    case (_, BadAnimal, 6) => -110
    case (_, BadAnimal, 7) => -100
    case (_, BadAnimal, _) => -90
  }
}
