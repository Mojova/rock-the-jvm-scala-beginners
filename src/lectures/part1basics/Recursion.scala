package lectures.part1basics

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }
  }
  println(factorial(10))
  // println(factorial(5000))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x < 1) accumulator
      else factHelper(x - 1, x * accumulator) // Tail recursion = use recursive call as the last expression
    }

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))

  // When you need loops, use tail recursion!

  def concatenator(input: String, times: Int): String = {
    @tailrec
    def concatenateHelper(times: Int, accumulator: String): String = {
      if (times == 0) accumulator
      else concatenateHelper(times - 1, accumulator + input)
    }
    concatenateHelper(times - 1, input)
  }

  println(concatenator("hello", 5))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def divide(dividend: Int, divisor: Int): Boolean = {
      if (divisor == 1) true
      else if (dividend % divisor == 0) false
      else divide(dividend, divisor - 1)
    }
    if (n == 1) true
    else divide(n, n - 1)
  }

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciHelper(m: Int, accumulator1: Int, accumulator2: Int): Int = {
      if (m == n) accumulator1 + accumulator2
      else fibonacciHelper(m + 1, accumulator1 + accumulator2, accumulator1)
    }
    if (n <= 2) 1
    else fibonacciHelper(3, 1, 1)
  }

  println(fibonacci(8))
}
