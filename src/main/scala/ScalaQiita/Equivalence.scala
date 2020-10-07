package ScalaQiita

object Equivalence {
  def main(): Unit = {
    class Domain1(val id: Long, val name: String) {
      def canEqual(other: Any) = {
        other.isInstanceOf[Domain1]
      }

      override def equals(other: Any) = {
        other match {
          case that: Domain1 =>
            that.canEqual(Domain1.this) && id == that.id && name == that.name
          case _ => false
        }
      }

      override def hashCode() = {
        val prime = 41
        prime * (prime + id.hashCode) + name.hashCode()
      }
    }

    val domain1 = new Domain1(1, "a")
    val domain2 = new Domain1(1, "a")
    val domain3 = new Domain1(2, "b")

    println(domain1 == domain3)
    println(domain1 != domain3)

  }
}
