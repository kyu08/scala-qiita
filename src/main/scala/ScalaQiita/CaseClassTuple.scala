package ScalaQiita

object CaseClassTuple {
  def get() = {
    val name = new Name("名前", "苗字")
    (Human(name, 15), name)
  }

  def main(): Unit ={
    val tuple = get()
    println(tuple._1.name.fullname)
    println(tuple._2.fullname)
  }

  class Name(first: String, last: String) {
    def fullname() = first + " " + last
  }

  case class Human(name: Name, age: Int)
}
