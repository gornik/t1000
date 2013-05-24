package t1000.geometry

import org.scalatest.FunSuite
import t1000.geometry.Direction._
import t1000.game.BasicActions
import BasicActions.Move

class VectorTests extends FunSuite {

  test("short vectors have correct direction") {
    assert(Vector(East.x, East.y).direction === East)
    assert(Vector(NorthEast.x, NorthEast.y).direction === NorthEast)
    assert(Vector(North.x, North.y).direction === North)
    assert(Vector(NorthWest.x, NorthWest.y).direction === NorthWest)
    assert(Vector(West.x, West.y).direction === West)
    assert(Vector(SouthWest.x, SouthWest.y).direction === SouthWest)
    assert(Vector(South.x, South.y).direction === South)
    assert(Vector(SouthEast.x, SouthEast.y).direction === SouthEast)
  }

  test("long vectors have correct directions") {
    assert(Vector(5, 4).direction === East)
    assert(Vector(0, 5).direction === South)
    assert(Vector(4, 4).direction === SouthEast)
    assert(Vector(5, -4).direction === East)
    assert(Vector(5, -5).direction === NorthEast)
    assert(Vector(1, -5).direction === North)
    assert(Vector(-4, -4).direction === NorthWest)
    assert(Vector(-4, -3).direction === West)
    assert(Vector(-4, 3).direction === West)

  }

  test("vectors have + and - operations") {
    assert(Vector(0, 0) + Vector(1, 1) === Vector(1, 1))
    assert(Vector(2, -3) + Vector(1, 1) === Vector(3, -2))
    assert(Vector(-2, 3) - Vector(-1, -1) === Vector(-1, 4))
  }

  test("vector distance is its chessboard length") {
    assert(Vector(0, 0).distance === 0)
    assert(Vector(1, 5).distance === 5)
    assert(Vector(-5, 4).distance === 5)
  }

  test("negative vectors have negative components") {
    assert(Vector(-1, 3).negative === Vector(1, -3))
    assert(Vector(2, 1).negative === Vector(-2, -1))
  }

  test("move towards is a move in direction of vector") {
    assert(Vector(East.x, East.y).moveTowards === Move(East))
    assert(Vector(North.x, North.y).moveTowards === Move(North))
  }

  test("move away from is a move in direction opposite of vector") {
    assert(Vector(East.x, East.y).moveAwayFrom === Move(West))
    assert(Vector(North.x, North.y).moveAwayFrom === Move(South))
  }
}
