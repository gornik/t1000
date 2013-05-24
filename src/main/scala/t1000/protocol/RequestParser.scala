package t1000.protocol


object RequestParser {
  val opCode = """(\w+)\((.*)\)""".r

  def parse(input: String): Request = {
    val opCode(name, rest) = input
    val params = rest.split(",").map {
      param =>
        val kv = param.split("=")
        (kv(0) -> kv(1))
    }.toMap

    name match {
      case "Welcome" => Welcome(
        params("name"),
        params("path"),
        params("apocalypse").toInt,
        params("round").toInt)
      case "React"   => React(
        params("generation").toInt,
        params("name"),
        params("time").toInt,
        params("view"),
        params("energy").toInt,
        params.getOrElse("master", ""),
        params -- Seq("generation", "name", "time", "view", "energy", "master"))
      case "Goodbye" => Goodbye(
        params("energy").toInt)
    }
  }
}
