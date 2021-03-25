package exercises

import scala.annotation.tailrec

abstract class MyList[+A] {

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def toString: String

  // higher order functions
  def map[B](transformer: A => B): MyList[B]
  def filter(filter: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object EmptyList extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B) = new Cons(element, EmptyList)

  override def toString = ""

  def map[B](transformer: Nothing => B): MyList[B] = EmptyList

  def filter(filter: Nothing => Boolean): MyList[Nothing] = EmptyList

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A] = EmptyList) extends MyList[A] {
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

  override def map[B](transformer: A => B): MyList[B] = {
    //@tailrec
    //def mapHelper(current: MyList[A], previous: MyList[B]): MyList[B] = {
    //  if (current.isEmpty) previous
    //  else {
    //    val transformed = transformer.transform(current.head)
    //    mapHelper(current.tail, previous.add(transformed))
    //  }
    //}

    // mapHelper(this, EmptyList)
    new Cons(transformer(h), t.map(transformer))
  }

  override def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] = transformer(h) ++ t.flatMap(transformer)
}

object ListExercise extends App {
  val list = Cons(5)
  println(list.add(2).add(3).toString)
  val stringList = Cons("foo")
  println(stringList.add("bar").add("xyz").add("abc").toString)
  val listWithStuff = list.add(2).add(3)
  println(listWithStuff.map((v1: Int) => v1 * 2).toString)
  println(listWithStuff.filter((v1: Int) => v1 % 2 == 0).toString)
}
