package ScalaQiita

object Currying {
  def main(): Unit ={
    val add = (x: Int, y: Int, z: Int) => x + y + z
    val addCurried = add.curried
    val addCurriedWithX = addCurried(1)
    val addCurriedWithY = addCurriedWithX(2)
    val addCurriedWithZ = addCurriedWithY(3)
    val hoge = addCurried(1)(2)(4)
    println(addCurried)
  }
}
