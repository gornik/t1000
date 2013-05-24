package t1000.strategies

import t1000.BotContext
import t1000.geometry.Direction

object Stay extends Strategy {
  def apply(ctx: BotContext, previous: StrategyResult) =
    previous.copy( directionMap =
      DirectionMap.initial.addDelta(Direction.None, 1000))
}
