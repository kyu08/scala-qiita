package ScalaQiita

object Partial {
  def main(): Unit ={
    def addPartial(x:Int, y:Int, z:Int) = x + y + z
    val addPartialWithZ = addPartial _
//    val addPartialWithZ = addPartial(_:Int, _:Int, 5)
//    println(addPartialWithZ(1,2))

  }
}
