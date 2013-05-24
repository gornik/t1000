package t1000.strategies

import t1000.BotContext
import t1000.game.Cell.Content.{Master}

//object SearchMaster extends Strategy {
//  def apply(ctx: BotContext, previousResult: StrategyResult) = {
//    previousResult.modifyMap(ctx.current.masterPosition.direction, 500)
//  }
//}

object SearchMaster extends MaximizeEnergy {
  override def energyFunction = {
    case (_, Master, 1) => 1000
    case (_, Master, 2) => 500
    case (_, Master, 3) => 400
    case (_, Master, 4) => 300
    case (_, Master, 5) => 200
    case (_, Master, 6) => 100
    case (_, Master, _) => 50
  }

  override def apply(ctx: BotContext, previousResult: StrategyResult) = {
    super.apply(ctx, previousResult).modifyMap(ctx.current.masterPosition.direction, 50)
  }

}