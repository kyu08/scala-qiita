package ScalaQiita

object Traversable {
  def main(): Unit ={
    println((Seq(1, 3, 4, 5, 6) partition  (_ > 4)))
  }
}
