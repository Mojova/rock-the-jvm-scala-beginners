package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  // switch on steroids
  val random = new Random()
  val x = random.nextInt(10)
  val description = x match {
    case 1 => "the One"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else" // _ = wildcard
  }
  println(x)
  println(description)

  // 1. decompose values
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi my name is ${n} and I’m ${a} years old and I can’t drink in the US"
    case Person(n, a) => s"Hi my name is ${n} and I’m ${a} years old"
    case _ => "I don’t know who I am"
  }
  println(greeting)

  /*
  1. cases are matched in order
  2. what if no cases match? MatchError
  3. type of the pattern match expression? Unified type of all the types in all the cases
  4. PM works really well with case classes
   */

  // PM on sealed hierarchies
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = new Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of dog of the $someBreed breed")
  }

  // match everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  // Why?!
  val isEvenCond = if (x % 2 == 0) true else false
  val isEvenNormal = x % 2 == 0

  trait Expr

  case class Number(number: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
      case Number(n) => n.toString
      case Sum(n1, n2) => s"${show(n1)} + ${show(n2)}"
      case Prod(n1, n2) => {
        def maybeShowParenthesis(exp: Expr) = exp match {
          case Prod(_, _) => show(exp)
          case Number(_) => show(exp)
          case Sum(m1, m2) => s"(${show(m1)} + ${show(m2)})"
        }
        s"${maybeShowParenthesis(n1)} * ${maybeShowParenthesis(n2)}"
      }
    }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))
}
