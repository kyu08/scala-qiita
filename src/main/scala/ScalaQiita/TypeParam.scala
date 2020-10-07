package ScalaQiita

object TypeParam {

  class TypeParam[T](val t: T) {
    def get: T = t
  }

  def main(): Unit ={
    val sTypeParam = new TypeParam[String]("hogehoge")
    println(sTypeParam.get)
  }
}
