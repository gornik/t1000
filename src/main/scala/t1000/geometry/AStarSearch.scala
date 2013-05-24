//package t1000.geometry
//
//import collection._
//import t1000.BasicActions.Move
//import t1000.game.View
//import t1000.geometry.Coordinates.Absolute
//
//class AStarSearch(view: View, goal: Vector){
//  def solve: Set[Move] = {
//    val start = Vector(0, 0)
//    val closedSet = mutable.Set[Vector]()
//    val openSet = mutable.Set[Vector](start)
//    val cameFrom = mutable.OpenHashMap[Vector, Vector]()
//
//    val gScore = mutable.OpenHashMap[Vector, Int](start -> 0)
//    val fScore = mutable.OpenHashMap[XY, Int](
//      start -> (gScore(start) + goal.distance))
//
//    while (!openSet.isEmpty) {
//      val current = openSet.minBy(x => fScore(x))
//      if (current == goal) {
//        val path = reconstructPath(cameFrom, goal)
//        if (path.length==2) {
//          return Set(path(1) - path(0))
//        } else {
//          // permute the options for step 2
//          val p1 = path(0)
//          val p2 = path(2)
//          val opts =
//            view.neighbours(p1).toSet intersect view.neighbours(p2).toSet
//          return opts.map(o => o - p1).toSet
//        }
//      }
//
//      openSet.remove(current)
//      closedSet.add(current)
//
//      view.neighbours(current).map(_.position).foreach(n => {
//        if (!closedSet.contains(n.absolute)) {
//          val tGScore = gScore(current) + 1
//          if (!openSet.contains(n) || (tGScore < gScore(n))) {
//            openSet.add(n)
//            cameFrom += (n -> current)
//            gScore += (n -> tGScore)
//            fScore += (n -> (gScore(n) + (n.toVector - goal.toVector).distance))
//          }
//        }
//      })
//    }
//
//    // failure
//    Set()
//  }
//
//  /**val move set */
//  val moves = Move.values - Move.Center
//
//  private def reconstructPath(cameFrom: Map[Vector, Vector],
//                              current: Vector): List[Vector] = {
//    if (cameFrom.contains(current)) {
//      reconstructPath(cameFrom, cameFrom(current)) ++ List(current)
//    } else {
//      List(current)
//    }
//  }
//}
