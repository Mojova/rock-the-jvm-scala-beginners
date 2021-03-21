package lectures.part2oop

object AbstractDataTypes extends App {
  // abstract
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("Crunch crunch")
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait Coldblooded

  class Crocodile extends Animal with Carnivore with Coldblooded {
    override val creatureType: String = "Croc"
    override def eat: Unit = println("Nom nom nom")
    override def eat(animal: Animal): Unit = println(s"I’m a croc and I’m eating ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)

  // traits vs abstract classes
  // 1. traits don’t have constructor parameters
  // 2. multiple traits may be inherited by the same class
  // 3. traits = behavior, abstract class = type of thing
}
