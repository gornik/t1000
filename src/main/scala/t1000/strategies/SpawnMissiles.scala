package t1000.strategies

import t1000.BotContext
import t1000.game.Cell.Content.{EnemySlave, Enemy}
import t1000.game.BasicActions
import BasicActions.Spawn

object SpawnMissiles extends Strategy {
  def apply(ctx: BotContext, previousResult: StrategyResult) = {
    ctx.current.view.nearest(Enemy, EnemySlave) match {
      case None => previousResult
      case Some(vector) =>
        previousResult
          .append(Spawn(vector.direction, "missile", 100, ("role", "missile")))
      }
    }
}

