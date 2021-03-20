package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  println(person.x)
  person.greet("Daniel")
  person.greet()

  val author = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy)

  val counter = new Counter
  counter.increment.print
  counter.increment.increment.increment.print
  counter.increment(10).print
}

// constructor
class Person(name: String, val age: Int = 0) {
  // body
  val x = 2

  println(1 + 3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // Overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName(): String = s"$firstName $surname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge = yearOfRelease - author.year
  def isWrittenBy: String = author.fullName()
  def copy(newYearOfRelease: Int) = new Novel(this.name, newYearOfRelease, this.author)
}

class Counter(value: Int = 0) {
  def currentCount = this.value
  def increment = new Counter(this.value + 1)
  def decrement = new Counter(this.value - 1)
  def increment(amount: Int): Counter = {
    if (amount <= 0) this
    else increment.increment(amount - 1)
  }
  def decrement(amount: Int): Counter = {
    if (amount <= 0) this
    else decrement.decrement(amount - 1)

  }
    def print = println(currentCount)
}

// Class parameters are not fields
