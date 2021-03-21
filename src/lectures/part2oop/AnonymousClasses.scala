package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat(): Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat(): Unit = println("ahahahaha")
  }

  funnyAnimal.eat
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is ${name}, how can I help?")

    val jim = new Person("Jim") {
      override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
    }
  }

}
