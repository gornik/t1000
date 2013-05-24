package t1000

import t1000.strategies.{Missile, AvoidObstacles}

class MissileBot extends Bot {
  val strategySet = AvoidObstacles + Missile
  def react(context: BotContext) = strategySet.bestAction(context, "Missile")
}
