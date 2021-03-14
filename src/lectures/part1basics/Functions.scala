package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  // When you need loops, use recursion!

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  def greet(name: String, age: Int): String = "Hi, my name is " + name + " and I am " + age + " years old."
  println(greet("John", 10))
  def factorial(n: Int): Int = {
    if (n == 1) 1
    else n * factorial(n - 1)
  }
  println(factorial(1))
  println(factorial(2))
  println(factorial(3))
  println(factorial(4))
  println(factorial(5))
  def fibonacci(n: Int): Int = {
    if (n == 1) 1
    else if (n == 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }
  println(fibonacci(3))
  println(fibonacci(4))
  println(fibonacci(5))
  println(fibonacci(8))
  def isPrime(n: Int): Boolean = {
     def divide(dividend: Int, divisor: Int): Boolean = {
       if (divisor == 1) true
       else if (dividend % divisor == 0) false
       else divide(dividend, divisor - 1)
     }
     if (n == 1) true
     else divide(n, n - 1)
  }
  println(isPrime(1))
  println(isPrime(2))
  println(isPrime(3))
  println(isPrime(4))
  println(isPrime(5))
  println(isPrime(6))
  println(isPrime(7))
  println(isPrime(8))
  println(isPrime(9))
  println(isPrime(10))
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}
