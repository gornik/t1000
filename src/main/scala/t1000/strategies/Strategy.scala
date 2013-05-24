package t1000.strategies

import t1000.{StrategySet, BotContext}
import t1000.geometry.Direction
import t1000.game.{BasicAction, BasicActions}
import BasicActions.Move
import t1000.protocol._

trait Strategy extends ((BotContext, StrategyResult) => StrategyResult) {
  def + (other: Strategy) = new StrategySet(this, other)
}

case class DirectionScored(direction: Direction, score: Int)

case class DirectionMap(scores: Seq[DirectionScored]) {

  lazy val scoreMap = scores.map(elem => elem.direction -> elem.score).toMap

  def addDelta(direction: Direction, delta: Int) = {
    scores.find(scored => scored.direction == direction) match {
      case None =>
        DirectionMap(scores ++ Seq(DirectionScored(direction, delta)))
      case Some(scored) =>
        DirectionMap(
          scores.map { case DirectionScored(dir, score) =>
            if (dir == direction) DirectionScored(dir, score + delta)
            else DirectionScored(dir, score)
          }
        )
    }
  }

  override def toString = scores
    .map(s => s.direction + "=" + s.score).mkString("|")

  def bestDirections: Seq[DirectionScored] =
    if (scores.isEmpty) Seq()
    else (Seq(scores.head) /: scores.tail) {
      (maxes, next) =>
        if (maxes.head.score > next.score) maxes
        else if (maxes.head.score == next.score) maxes :+ next
        else Seq(next)
    }

  def bestMove = {
    val allBestMoves = bestDirections.map(_.direction.move).toArray
    allBestMoves.length match {
      case 0 => Seq()
      case 1 => Seq(allBestMoves.head)
      case n => Seq(allBestMoves(new java.util.Random().nextInt(n)))
    }
  }
}

object DirectionMap {
  val initial = DirectionMap(Direction.all.map(DirectionScored(_, 0)))
}

case class StrategyResult(
  directionMap: DirectionMap,
  otherActions: List[BasicAction]) {

  def actions: Seq[BasicAction] = {
    val bestMoves = directionMap.bestMove
    if (!bestMoves.isEmpty) bestMoves ++ otherActions ++
      Seq(BasicActions.Set(("prevDir", bestMoves.head.direction)))
    else otherActions
  }

  def append(action: BasicAction) = StrategyResult(directionMap,
    action :: otherActions)

  def modifyMap(direction: Direction, energyDelta: Int) = {
    StrategyResult(directionMap.addDelta(direction, energyDelta), otherActions)
  }
}

object StrategyResult {
  val none = StrategyResult(DirectionMap.initial, Nil)
}