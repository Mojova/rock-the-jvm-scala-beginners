package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  // println(x.length)

  // 1. throwing and catching exceptions

  // val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes

  // 2. How to catch exceptions
  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("No int for you")
    else 42
  }

  val potentialFail = try {
    // code that might fail
    getInt(false)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will get executed no matter what
    // optional
    // does not influence the return type of this expression
    // only for side effects
    println("finally")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception

  val exception = new MyException

  //throw exception

  def memoryHog(accumulator: String): String = {
    memoryHog("foobar" + accumulator + accumulator)
  }

  // memoryHog("go!")

  def stackOverFlower(number: Int): Int = {
    stackOverFlower(number + 2) + 1
  }

  // stackOverFlower(1)

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val sum = x + y
      if (x > 0 && y > 0 && sum < 0) throw new OverflowException
      else if (x < 0 && y < 0 && sum > 0) throw new UnderflowException
      sum
    }
    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new OverflowException
      result
    }
    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      x / y
    }

  }

  // println(PocketCalculator.add(Int.MaxValue, 10).toString)
  // println(PocketCalculator.divide(1, 0))
}
