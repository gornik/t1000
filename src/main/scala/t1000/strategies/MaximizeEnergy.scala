package t1000.strategies

import t1000.{BotContext}
import t1000.game.Cell

trait MaximizeEnergy extends Strategy {

  def apply(context: BotContext, previousBest: StrategyResult) = {

    var result = previousBest

    context.current.view.BotsPerspective.cells.foreach { cell =>
      val distance = cell.position.toVector.distance
      if (energyFunction.isDefinedAt(context, cell.content, distance))
        result = result.modifyMap(
          cell.position.toVector.direction,
          energyFunction.apply(context, cell.content, distance))
    }
    result
  }

  def energyFunction: PartialFunction[(BotContext, Cell.Content, Int), Int]

}



