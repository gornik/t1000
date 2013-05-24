package t1000.strategies

import t1000.game.Cell.Content.{GoodAnimal, GoodPlant}

object EatFood extends MaximizeEnergy {
  override def energyFunction = {
    case (_, GoodPlant, 1) => 100
    case (_, GoodPlant, 2) => 50
    case (_, GoodPlant, 3) => 30
    case (_, GoodPlant, 4) => 20
    case (_, GoodPlant, 5) => 15
    case (_, GoodPlant, 6) => 10
    case (_, GoodPlant, 7) => 9
    case (_, GoodPlant, 8) => 8
    case (_, GoodPlant, 9) => 7
    case (_, GoodPlant, _) => 6
    case (_, GoodAnimal, 1) => 200
    case (_, GoodAnimal, 2) => 100
    case (_, GoodAnimal, 3) => 50
    case (_, GoodAnimal, 4) => 40
    case (_, GoodAnimal, 5) => 30
    case (_, GoodAnimal, 6) => 20
    case (_, GoodAnimal, 7) => 10
    case (_, GoodAnimal, _) => 9
  }
}
