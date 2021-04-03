package lectures.part3fp

object TuplesAndMaps extends App {
  //  tuples = finite, ordered “lists”
  val aTuple = (2, "hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye, Java"))
  println(aTuple.swap)

  //  Maps - keys - values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map("Jim" -> 555, "Daniel" -> 789).withDefaultValue(-1)
  //  a -> b is sugar for (a, b)
  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary"))

  //  add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phoneBook + newPairing
  println(newPhonebook)

  //  functionals on map
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  //  filterKeys
  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap)
  //  mapValues
  println(phoneBook.view.mapValues(number => "0245-" + number * 10).toMap)

//  conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  val doubleMap = Map("Jim" -> 123, "JIM" -> 456).map(pair => pair._1.toLowerCase -> pair._2)
  println(doubleMap)
}

