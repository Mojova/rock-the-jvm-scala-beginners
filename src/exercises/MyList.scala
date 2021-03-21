package exercises

import scala.annotation.tailrec

abstract class MyList[+A] {

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def toString: String
}

object EmptyList extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](element: B) = new Cons(element, EmptyList)
  override def toString = ""
}

class Cons[+A](h: A, t: MyList[A] = EmptyList) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B) = new Cons(element, this)

  override def toString = {
    @tailrec
    def stringHelper(current: MyList[A], accumulator: String = "["): String = {
      if (current.isEmpty) accumulator + "]"
      else stringHelper(current.tail, s"${accumulator} ${current.head.toString}")
    }
    stringHelper(this)
  }
}

object ListExercise extends App {
  val list = new Cons(5)
  println(list.add(2).add(3).toString)
  val stringList = new Cons("foo")
  println(stringList.add("bar").add("xyz").add("abc").toString)
}
