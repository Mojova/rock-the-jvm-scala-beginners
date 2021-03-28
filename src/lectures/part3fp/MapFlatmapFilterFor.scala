package lectures.part3fp

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list)

  // map
  println(list.head)
  println(list.tail)

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between to lists
  val numbers = List(1, 2, 3, 4)
  val characters = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  val combinations = numbers.flatMap(x => characters.flatMap(y => colors.map(color => y + x.toString + color)))
  println(combinations)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- characters
    color <- colors
  } yield c + n.toString + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

}
