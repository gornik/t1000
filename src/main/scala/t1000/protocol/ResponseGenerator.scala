package t1000.protocol

import t1000.game.{BasicAction, BasicActions}

object ResponseGenerator {

  import BasicActions._

  def createResponseFrom(actions: Seq[BasicAction]): String = actions
    .map(createFromAction).mkString("|")

  private def createFromAction(action: BasicAction): String = action match {
    case Move(direction) =>
      makeCommand("Move", "direction" -> direction)
    case Spawn(direction, name, energy, additional@_*) => {
      val params = Seq[(String, String)](
        "direction" -> direction,
        "name" -> name,
        "energy" -> energy.toString) ++ additional
      makeCommand("Spawn", params: _*)
    }
    case Set(params@_*) =>
      makeCommand("Set", params: _*)
    case Explode(size) =>
      makeCommand("Explode", "size" -> size.toString)
    case Say(text) =>
      makeCommand("Say", "text" -> text)
    case Status(text) =>
      makeCommand("Status", "text" -> text)
    case MarkCell(position) =>
      makeCommand("MarkCell", "position" -> position)
    case DrawLine(from, to, color) =>
      makeCommand("DrawLine", "from" -> from, "to" -> to, "color" -> color)
    case Log(text) =>
      makeCommand("Log", "text" -> text)
    case NoAction => ""
  }

  private def makeCommand(opCode: String, params: (String, String)*) =
    opCode + "(" + params.map {
      case (param, value) => param + "=" + value
    }.mkString(",") + ")"
}
