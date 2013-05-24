package t1000

import t1000.protocol._
import t1000.geometry.Vector
import t1000.game.View

object BotContext {
  var roundContext: RoundContext = RoundContext("", "", 0, 0)

  def apply(request: Request): Option[BotContext] = request match {
    case Welcome(name, path, apocalypse, round) => {
      Logger.initialize(path)
      roundContext = new RoundContext(name, path, apocalypse, round)
      None
    }
    case React(generation, name, time, view, energy, master, state) => {
      Some(
        new BotContext(name, energy, generation, roundContext,
          new MoveContext(time, view, master), state))
    }
    case Goodbye(energy) => {Logger.logFinish(energy); None}
  }

  case class RoundContext(masterBotName: String,
                          currentPath: String,
                          apocalypse: Int,
                          round: Int)

  case class MoveContext(time: Int,
                         view: View,
                         masterPosition: Vector)

}

case class BotContext(botName: String,
                      energy: Int,
                      generation: Int,
                      game: BotContext.RoundContext,
                      current: BotContext.MoveContext,
                      state: Map[String, String]) {
  def isMaster = generation == 0

  def remainingTime = game.apocalypse - current.time
}


