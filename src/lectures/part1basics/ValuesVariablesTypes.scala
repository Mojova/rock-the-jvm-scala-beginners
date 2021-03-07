package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x = 42
  println(x)

  // vals are immutable

  val aString: String = "Hello";
  val anotherString = "Goodbye"

  val aBoolean: Boolean = true
  val aCharacter: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 32767
  val aLong: Long = 107374182713434L
  val aFloat: Float = 2.0f
  val aDoulbe: Double = 3.14

  // variables
  var aVariable: Int = 4
  aVariable = 5 // side effects
  
}
