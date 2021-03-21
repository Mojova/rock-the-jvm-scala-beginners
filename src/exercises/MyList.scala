package exercises

import scala.annotation.tailrec
import scala.runtime.Nothing$

abstract class MyList {

  def head: Int

  def tail: MyList

  def isEmpty: Boolean

  def add(number: Int): MyList

  def toString: String
}

object EmptyList extends MyList {
  override def head: Int = throw new NoSuchElementException
  override def tail: MyList = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add(number: Int) = new Cons(number, EmptyList)
  override def toString = ""
}

class Cons(h: Int, t: MyList = EmptyList) extends MyList {
  override def head = h
  override def tail: MyList = t
  override def isEmpty: Boolean = false
  override def add(number: Int) = new Cons(number, this)

  override def toString = {
    @tailrec
    def stringHelper(current: MyList, accumulator: String = ""): String = {
      if (current.isEmpty) accumulator
      else stringHelper(current.tail, s"${accumulator} ${current.head.toString}")
    }
    stringHelper(this)
  }
}

object ListExercise extends App {
  val list = new Cons(5)
  println(list.add(2).add(3).toString)
}
