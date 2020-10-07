package ScalaQiita

object Identity {
  def main(): Unit ={
    class Domain2(val id: Long, val name: String) {
      def canEqual(other: Any) = {
        other.isInstanceOf[Domain2]
      }

      override def equals(other: Any) = {
        other match {
          case that: Domain2 =>
            that.canEqual(Domain2.this) && id == that.id && name == that.name
          case _ => false
        }
      }
    }

    val domain1 = new Domain2(1, "hoge")
    val domain2 = domain1
    val domain3 = new Domain2(1, "hoge")

    println(domain1 eq domain3)
    println(domain1 ne domain3)

  }

}
