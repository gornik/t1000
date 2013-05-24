package t1000.protocol

sealed trait Request

case class Welcome(name: String,
                   path: String,
                   apocalypse: Int,
                   round: Int) extends Request

case class React(generation: Int,
                 name: String,
                 time: Int,
                 view: String,
                 energy: Int,
                 master: String,
                 state: Map[String, String]) extends Request

case class Goodbye(energy: Int) extends Request