import sbt._
import Keys._

object Build extends Build {
  val botDirectory = SettingKey[File]("bot-directory")
  val play = TaskKey[Unit]("play")
 
  val bot = Project(
    id = "t1000", 
    base = file("."), 
    settings = Project.defaultSettings ++ botSettings)
      
  val botSettings = Seq[Setting[_]](
      name := "t1000",
      version := "1.0",
 
      scalaVersion := "2.9.3",
      scalacOptions ++= Seq("-deprecation", "-unchecked", "-Ydependent-method-types"),
 
      javaOptions += "-Xmx1g",
 
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "1.9.1" % "test"),
       
      botDirectory := file("bots"),
 
      play <<= (botDirectory, name, javaOptions, 
        unmanagedClasspath in Compile, 
        Keys.`package` in Compile) map { 
        (bots, name, javaOptions, ucp, botJar) =>
        
        IO createDirectory (bots / name)
        IO copyFile (botJar, bots / name / "ScalatronBot.jar")

        val cmd = "java %s -jar %s -plugins %s -browser no -maxslaves 100 " +
          "-rounds 20 -x 100 -y 100" format (
          javaOptions mkString " ",
          ucp.files.head.absolutePath,
          bots.absolutePath)
        cmd.run()
      }
    )
}