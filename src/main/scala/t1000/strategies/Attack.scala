package t1000.strategies

import t1000.BotContext
import t1000.game.Cell.Content._
import t1000.game.BasicActions
import BasicActions.Explode

object Attack extends Strategy {
  def apply(ctx: BotContext, previousResult: StrategyResult) =
    if (ctx.isMaster) previousResult
    else
      ctx.current.view.nearest(Enemy) match {
        case None => previousResult
        case Some(vector) if vector.distance > 2 =>
          previousResult.modifyMap(vector.direction, 200)
        case Some(vector) => previousResult.append(Explode(2))
      }

}
