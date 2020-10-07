package ScalaQiita

object ControlSyntax {
  def main(): Unit= {
    try {
      val input = new java.io.FileInputStream("input.txt")
    } catch {
      case ex: java.io.FileNotFoundException => println("no")
      case ex: java.io.IOException => println("e")
    } finally {
      println("owatade")
    }

  }
}
