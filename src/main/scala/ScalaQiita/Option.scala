package ScalaQiita

object Option {
  def main(): Unit = {
    val map = Map(1 -> "ichi", 2 -> "ni", 3 -> "san")

    def check(o: Option[String]): Unit = {
      o match {
        case Some(s) => println(s)
//        case None => println("None detected.")
      }
    }

    val some = map get(2)
    val none = map get(5)

    check(some)
    check(none)
  }
}
