package t1000.geometry

import org.scalatest.FunSuite

class CoordinatesTests extends FunSuite {

  val relative = Coordinates.relativeFrom(Coordinates.Absolute.Point(2, 1))

  test("translates absolute point to other coordinates") {
    assert(Coordinates.Absolute.Point(3, 2).translateTo(relative) ===
      relative.Point(1, 1))
    assert(Coordinates.Absolute.Point(0, 0).translateTo(relative) ===
      relative.Point(-2, -1))
  }

  test("translation from absolute to absolute coordinates has no effect") {
    assert(Coordinates.Absolute.Point(3, 2).translateTo(Coordinates.Absolute) ===
      Coordinates.Absolute.Point(3, 2))
  }

  test("captures coordinates") {
    assert(Coordinates.Absolute.Point(0, 0).coordinates === Coordinates.Absolute)
    assert(relative.Point(0, 0).coordinates === relative)
  }

}
