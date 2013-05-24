package t1000

import t1000.strategies._

class WorkerBot extends Bot {
  def chooseStrategy(context: BotContext)  =
    if (context.remainingTime < 3 * context.current.masterPosition.distance ||
      context.remainingTime < context.game.apocalypse / 50)
      SearchMaster + AvoidOwnMiniBots + AvoidObstacles
    else
      BaseStrategySet + SpreadOut

  def react(context: BotContext) = chooseStrategy(context).bestAction(context)
}
