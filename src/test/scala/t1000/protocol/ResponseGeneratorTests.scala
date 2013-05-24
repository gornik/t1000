package t1000.protocol

import org.scalatest.FunSuite
import t1000.game.{BasicAction, BasicActions}
import BasicActions._
import t1000.geometry.{Direction, Vector}

class ResponseGeneratorTests extends FunSuite {
  test("can create Move command") {
    assert(create(Move(Direction.SouthEast)) === "Move(direction=1:1)")
  }

  test("can create Spawn command") {
    val action = Spawn(Direction.SouthEast, "name", 120,
      "role" -> "missile", "param" -> "value")

    assert(create(action) ===
      "Spawn(direction=1:1,name=name,energy=120,role=missile,param=value)")
  }

  test("can create Set command") {
    assert(create(Set("name" -> "value")) === "Set(name=value)")
  }

  test("can create Explode command") {
    assert(create(Explode(10)) === "Explode(size=10)")
  }

  test("can create Say command") {
    assert(create(Say("hello")) === "Say(text=hello)")
  }

  test("can create Status command") {
    assert(create(Status("hello")) === "Status(text=hello)")
  }

  test("can create MarkCell command") {
    assert(create(MarkCell(Vector(2, 1))) === "MarkCell(position=2:1)")
  }

  test("can create DrawLine command") {
    val action = DrawLine(Vector(2, 1), Vector(1, 2), "#123456")
    assert(create(action) === "DrawLine(from=2:1,to=1:2,color=#123456)")
  }

  test("can create Log command") {
    assert(create(Log("hello")) === "Log(text=hello)")
  }

  test("multiple commands are separated by |") {
    val actions = Seq(Explode(10), Say("Kill"), Log("explosion 10"))
    assert(ResponseGenerator.createResponseFrom(actions) ===
      "Explode(size=10)|Say(text=Kill)|Log(text=explosion 10)")
  }

  private def create(action: BasicAction) =
    ResponseGenerator.createResponseFrom(Seq(action))
}
