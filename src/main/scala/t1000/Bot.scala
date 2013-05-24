package t1000

import t1000.strategies._
import t1000.game.BasicAction

object Bot {
  def react(context: Option[BotContext]): Seq[BasicAction] = context match {
    case None => Seq()
    case Some(ctx) => {
      val bot =
        if (ctx.isMaster) new MasterBot
        else ctx.state("role") match {
          case "missile" => new MissileBot
          case _ => new WorkerBot
        }
      bot.react(ctx)
    }
  }
}

trait Bot {
  def react(context: BotContext) : Seq[BasicAction]
}

object BaseStrategySet extends StrategySet(
  EatFood, AvoidAnimals, AvoidObstacles,
  AvoidOtherBots, SpawnWorkers, MaintainDirection)


















