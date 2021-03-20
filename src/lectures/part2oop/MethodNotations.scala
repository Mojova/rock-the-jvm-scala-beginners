package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = favoriteMovie == movie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(str: String): Person = new Person(s"$name ($str)", favoriteMovie)
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def unary_! : String = s"$name what the heck?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times"
    def learns(subject: String): String = s"$name learns $subject"
    def learnScala: String = learns("Scala")
  }

  val mary =  new Person("Mary", "Inception", 21)
  println(mary.likes("Inception"))
  println(mary likes "Inception")
  // infix notation / operator notation (syntactic sugar)

  // “operators” in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // All operators are methods
  // Akka actors have ! ?

  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + tilde !
  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary())

  println(mary + "rockstar")
  println(+mary.age)
  println(mary learns "Java")
  println(mary learnScala)
  println(mary(2))
}
