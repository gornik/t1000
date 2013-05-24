package t1000

import t1000.strategies.{LureEnemySlaves, SearchSlaves, SpawnWorkers, AvoidObstacles}

class MasterBot extends Bot {

  def chooseStrategy(context: BotContext)  =
    if (context.current.time < 100)
      AvoidObstacles + SpawnWorkers
    else if (context.remainingTime < context.game.apocalypse / 5 )
      BaseStrategySet + SearchSlaves
    else BaseStrategySet + LureEnemySlaves

  def react(context: BotContext) = chooseStrategy(context).bestAction(context)
}
