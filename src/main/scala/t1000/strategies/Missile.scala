package t1000.strategies

import t1000.BotContext
import t1000.game.Cell.Content.{EnemySlave, Enemy}
import t1000.game.BasicActions
import BasicActions.Explode

object Missile extends Strategy {
  def apply(ctx: BotContext, previousResult: StrategyResult) =
    if (ctx.isMaster) previousResult
    else
      ctx.current.view.nearest(Enemy, EnemySlave) match {
        case None => SearchMaster(ctx, previousResult)
        case Some(vector) if vector.distance > 1 =>
          previousResult.modifyMap(vector.direction, 900)
        case Some(vector) => previousResult.append(Explode(2))
      }
}
