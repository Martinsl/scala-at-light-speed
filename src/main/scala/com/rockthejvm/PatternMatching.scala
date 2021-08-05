package com.rockthejvm

object PatternMatching extends App {
  // switch expression
  val anInteger = 55
  
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }
  // PM is an EXPRESSION - we can assign it to a value

  // Case class decomposition
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 43)

  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n,and I'm $a years old."
    case _ => "Heyo"
  }

  // deconstructing tuples
  val aTuple = ("Bon Jove", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre"
    case _ => "I don't know what you are talking about"
  }

  // decomposing Lists
  val aList = List(1,2,3)
  val listDescription = aList match {
    case List(_, 2, _) => "List containing 2 on the second position"
    case _ => "unknown list"
  }

  // if PM doesn't match anything, it will throw a MatchError, which will crash your program
  // PM will try all cases in sequence, default case HAS to be last

}