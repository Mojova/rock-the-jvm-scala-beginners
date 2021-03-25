package lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // problem: oop
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // Function types = Function1[A, B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: (Int, Int) => Int = (v1: Int, v2: Int) => v1 + v2
  // Function types Function2[A, B, R] === (A, B) => R

  // All Scala functions are objects

  val concatenate = (v1: String, v2: String) => v1 + v2

  val inter: Int => Int => Int = (v1: Int) => (v2: Int) => v1 + v2

  println(inter(1)(2))
  println(concatenate("foo", "bar"))
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
