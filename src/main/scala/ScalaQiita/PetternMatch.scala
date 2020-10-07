package ScalaQiita

object PetternMatch {
  def main(): Unit ={
    case class Person(val id: Int, val age: Int, val name: String)
    val person = Person(1, 25, "taro")
    person match {
      case Person(1, 23, "taro") => println("OK")
      case Person(_, 24, _) => println("BAD")
      case _ => println("NO")

    }

  }
}
