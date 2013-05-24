package t1000.protocol

import org.scalatest.FunSuite

class RequestParserTests extends FunSuite {

  test("can parse Welcome command") {
    assert(
      parse(
        "Welcome(name=bot,path=c:\\Code\\bots,apocalypse=1,round=5)") ===
        Welcome("bot", "c:\\Code\\bots", 1, 5))
  }

  test("can parse React command") {
    assert(
      parse(
        "React(generation=1,name=bot,time=4,view=_W__,energy=50)") ===
        React(1, "bot", 4, "_W__", 50, "", Map()))
  }

  test("can parse React command with master param") {
    assert(
      parse(
        "React(generation=1,name=bot,time=4,view=_W__,energy=50,master=1:1)") ===
        React(1, "bot", 4, "_W__", 50, "1:1", Map()))
  }

  test("can parse React command with custom state") {
    assert(
      parse(
        "React(generation=1,name=bot,time=4,view=_W__,energy=50,custom=value)") ===
        React(1, "bot", 4, "_W__", 50, "", Map("custom" -> "value")))
  }

  test("can parse Goodbye command") {
    assert(parse("Goodbye(energy=50)") === Goodbye(50))
  }

  test("comon error") {
    assert(parse("React(generation=0,time=1482," +
      "view=_______??WWWWW_____????????????________?W_________" +
      "????????????________?W________?????????????_________W__p_____" +
      "?????????????__________________WWWWWWWWWWWWW________________p_WWWWWWWWWWWWW______P____________p_________________________________________________________________P______________________________________________________________________________________________________________________________________________________________????_________________________W?????_______________M_________W?????_________________________W?????_________________________W?????____________________________???_______________________P____________P________________________________________________________?W_____________________________?W_____________________________?W_____________________________?WWW__________________m________???____________________________??_____________________________?______________________________________________________________________b_____________________,energy=5000,name=t1000)") === Goodbye(50))
  }

  private def parse(s: String): Request = RequestParser.parse(s)
}