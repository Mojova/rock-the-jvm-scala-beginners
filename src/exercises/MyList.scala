package exercises

import scala.annotation.tailrec

abstract class MyList[+A] {

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def toString: String

  def map[B](transformer: MyTransformer[A, B]): MyList[B]

  def filter(filter: MyPredicate[A]): MyList[A]

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object EmptyList extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B) = new Cons(element, EmptyList)

  override def toString = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = EmptyList

  def filter(filter: MyPredicate[Nothing]): MyList[Nothing] = EmptyList

  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = EmptyList

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

  override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    //@tailrec
    //def mapHelper(current: MyList[A], previous: MyList[B]): MyList[B] = {
    //  if (current.isEmpty) previous
    //  else {
    //    val transformed = transformer.transform(current.head)
    //    mapHelper(current.tail, previous.add(transformed))
    //  }
    //}

    // mapHelper(this, EmptyList)
    new Cons(transformer.transform(h), t.map(transformer))
  }

  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = transformer.transform(h) ++ t.flatMap(transformer)
}

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}

object EvenPredicate extends MyPredicate[Int] {
  override def test(element: Int): Boolean = element % 2 == 0
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  override def transform(element: String): Int = element.toInt
}

object Doubler extends MyTransformer[Int, Int] {
  override def transform(element: Int): Int = element * 2
}

object ListExercise extends App {
  val list = Cons(5)
  println(list.add(2).add(3).toString)
  val stringList = Cons("foo")
  println(stringList.add("bar").add("xyz").add("abc").toString)
  val listWithStuff = list.add(2).add(3)
  println(listWithStuff.map(Doubler).toString)
  println(listWithStuff.filter(EvenPredicate).toString)
}
