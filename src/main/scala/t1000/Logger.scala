package t1000

import java.io._

object Logger {
  var file: String = ""

  def initialize(path: String) {
    file = path + "\\log.txt";
    val output = new File(file)
    val writer = new BufferedWriter(new FileWriter(output, true))
    writer.write("Initializing " + file)
    writer.newLine()
    writer.flush()
    writer.close()
  }

  def  logFinish(energy: Int) {

    val output = new File(file)
    val writer = new BufferedWriter(new FileWriter(output, true))
    writer.write("Finished round: " + energy.toString)
    writer.newLine()
    writer.flush()
    writer.close()
  }

  def log(s: String) {
    if (file == "")
      return;

//    val output = new File(file)
//    val writer = new BufferedWriter(new FileWriter(output, true))
//    writer.write(s)
//    writer.newLine()
//    writer.flush()
//    writer.close()
  }

  def log(e: Exception) {
    if (file == "")
      return;

    val output = new File(file)
    val writer = new BufferedWriter(new FileWriter(output, true))
    writer.write(e.toString)
    writer.newLine()
    e.getStackTrace.foreach {
      line => writer.write(line.toString); writer.newLine()
    }
    writer.flush()
    writer.close()
  }
}
