package t1000.strategies

import t1000.BotContext
import t1000.game.BasicActions
import BasicActions.Spawn
import t1000.geometry.Direction

object SpawnWorkers extends Strategy {
  def apply(ctx: BotContext, previousResult: StrategyResult) =
    if (ctx.energy > 200)
      previousResult.append(
        Spawn(previousResult.directionMap.bestMove.headOption
          .getOrElse(Direction.East.move).direction.negative,
          "offspring", 100, ("role", "worker")))
    else
      previousResult
}


