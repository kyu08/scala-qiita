package ScalaQiita

object Collection {
  def main(): Unit ={
    val map = Map(1 -> "a", 2 -> "b")
    trait Job { val name: String }
    println(new Job{val name = "j"})
  }
}
