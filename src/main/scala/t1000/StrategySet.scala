package t1000

import t1000.strategies.{StrategyResult, Strategy}
import t1000.game.BasicActions
import BasicActions.Status

class StrategySet(strategies: Strategy*) {

  def bestAction(context: BotContext, status: String = "") = {
    val actions = strategies.foldLeft(StrategyResult.none)(
      (result, strategy) => strategy(context, result)).actions

    actions ++ (if (status.isEmpty) Seq() else Seq(Status(status)))
  }

  def + (strategy: Strategy) = new StrategySet(strategies ++ Seq(strategy): _*)
}
