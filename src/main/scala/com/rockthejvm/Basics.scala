package com.rockthejvm

object Basics extends App {

  //defining a value
  val meaningOfLife: Int = 42

  // Int, Boolean, Char, Double, Float, String
  val aBoolean = false // type is optional

  val aString = "I love Scala"
  val aComposedString = "I" + " " + "love" + " " + "Scala"
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

  // expressions = structures that can be reduced to a value
  val anExpression = 2 + 3

  // if-expression
  val ifExpression = if (meaningOfLife > 43) 56 else 999
  val chainedIfExpression = 
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 78
    else 0

  // code blocks
  val aCodeBlock: Int = {
    // you can add definitions
    val aLocalValue = 67

    // at the end you have to return something
    // value of the block is the value of the last expression
    aLocalValue + 3
  }

  // define a function
  // code block is also an expression
  def myFunction(x: Int, y: String): String = {
    y + " " + x
  }

  // recusive expression example
  def factorial(n: Int): Int = 
    if (n <= 1) 1
    else n * factorial(n -1)


  // the Unit type = no meaningful value ~= "void" in other langauges
  // type of SIDE EFFECTS
  // println(x: Any): Unit
  println("I love Scala")

  def myUnitReturningFunction(): Unit = {
    println("I don't love returning Unit")
  }

  val theUnit = ()
}