package t1000.strategies

import t1000.BotContext
import t1000.protocol._

object MaintainDirection extends Strategy {
  def apply(ctx: BotContext, previous: StrategyResult) = {
    val prevDir = ctx.state.getOrElse("prevDir", "1:0").direction
    previous.modifyMap(prevDir, 20)
  }
}
