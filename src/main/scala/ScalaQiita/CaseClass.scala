package ScalaQiita

object CaseClass {
  def main(): Unit ={
    case class Person(val id: Int, val name: String)

    val person1 = Person(1, "john")
    val person2 = Person(1, "john")
    println(person1 == person2)
  }
}
