package ScalaQiita

object Tuple {
  def getMaxValue(numbers: List[Int]): (Int, Int) = {
    val max = numbers.max
    val index = numbers.indexOf(max)
    (max, index)
  }

  def main() = {
    val numbers = List(1,2,3)
    println(s"${getMaxValue(numbers)._2}")
  }
}
