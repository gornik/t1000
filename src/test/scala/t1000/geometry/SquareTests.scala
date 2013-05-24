package t1000.geometry

import org.scalatest.FunSuite

class SquareTests extends FunSuite {

  val values: String =
    "___W_" +
    "_____" +
    "_____" +
    "_____" +
    "__b__"

  val square = new Square[Char](values)

  test("prints square") {

    assert(square.toString ===
      "_ _ _ W _\n" +
      "_ _ _ _ _\n" +
      "_ _ _ _ _\n" +
      "_ _ _ _ _\n" +
      "_ _ b _ _")
  }

  test("decodes content with y axis inverted") {
    assert(square.contentAt(Coordinates.Absolute.Point(0, 3)) === Some('W'))
    assert(square.contentAt(Coordinates.Absolute.Point(4, 2)) === Some('b'))
    assert(square.contentAt(Coordinates.Absolute.Point(1, -1)) === None)
  }

}
