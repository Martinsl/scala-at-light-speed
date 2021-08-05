package com.rockthejvm

object FunctionalProgramming extends App {
  // Scala is OO
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  }

  val bob = new Person("Bob")
  bob(43) // "invoking" bob as a function ~= bob.apply(43)

  /**
    * Scala runs on the JVM
    * In Functional programming:
    * - compose functions
    * - pass functions as args
    * - return functions as results
    *
    * Conclusion FunctionX = Function1, Function2, ... Function22
    */


    val simpleIncrementer = new Function1[Int, Int] {
      override def apply(arg: Int): Int = arg +1
    }

    simpleIncrementer.apply(23) // 24
    simpleIncrementer(23) // 24
    // basically we defined a function

    // all scala functions are instances of these FUNCTION_X types

    // function with 2 arguments and a String return type
    val stringConcatenator = new Function2[String, String, String] {
      override def apply(arg1: String, arg2: String): String = arg1 + arg2
    }
    stringConcatenator("I love", "Scala") // "I love Scala"

    // syntax sugars
    val doubler: Function1[Int, Int] = (x: Int) => 2* x
    doubler(4) // 8
    /*
      equivalent to
      new Function1[Int, Int] {
        override def apply(x: Int) = 2 * x
      }
    */

    val doubler2: Int => Int = (x: Int) => 2* x
    doubler2(4) // 8
    /*
      equivalent to the much longer
      val doubler2: Function1[Int, Int] = new Function[Int, Int] {
        override def apply(x: Int) = 2 * x
      }
    */

    // sometimes we can avoid typing the types
    val doubler3 = (x: Int) => 2* x
    doubler3(4) // 8

    // higher-order functions: take functions as args/return functions as results
    val aMappedList: List[Int] = List(1,2,3).map(x => x + 1) // HOF
    val aFlatMappedList = List(1,2,3).flatMap { x =>
      List(x, 2 * x)
    } // alternative syntax
    val aFilteredList = List(1,2,3,4,5).filter(x => x <= 3)
    val aFilteredList2 = List(1,2,3,4,5).filter(_ <= 3) // equivalent to x => x <= 3

    // all pairs between the numbers 1,2,3 and letters 'a', 'b', 'c'
    var allPairs = List(1,2,3).flatMap(num => List('a', 'b', 'c').map(letter => s"$num-$letter"))

    //  for comprehensions - equivalent to the map/flatMap chain above, these two are IDENTICAL to the COMPILER
    val alternativePairs = for {
      number <- List(1,2,3)
      letter <- List('a','b','c')
    } yield s"$number-$letter"
    

    /**
      * Collections
    */
    
    // lists
    val aList = List(1, 2, 3, 4, 5)
    val firstElement = aList.head
    val rest = aList.tail
    val aPrependedList = 0 :: aList // prepend - List (0,1,2,3,4,5)
    val anExtendedList = 0 +: aList :+ 6 // +: prepends, :+ appends - List(0,1,2,3,4,5,6)

    // sequendes
    val aSequence: Seq[Int] = Seq(1,2,3) // Seq.apply(1,2,3)
    val accessedElemnet = aSequence(1) // access element by index

    // vectors? fast Seq implementation
    val aVector = Vector(1,2,3,4,5)

    // sets = no duplicates
    val aSet = Set(1,2,3,4,1,2,3) // Set(1,2,3,4)
    val setHas5 = aSet.contains(5) // false
    val anAddedSet = aSet + 5 // Set(1,2,3,4,5) - not necessarily in this order
    val aRemovedSet = aSet - 3 // Set(1,2,4)

    // ranges
    val aRange = 1 to 1000 // does not contain, but can be processed as if it did
    val twoByTwo = aRange.map(x => 2 * x).toList // List(2,4,6,8,..., 2000)

    // tuples = groups of values under the same value
    val aTuple = ("Bon Jovi", "Rock", 1982)

    // maps - key value
    val aPhonebook: Map[String, Int] = Map(
      ("Daniel", 812391283),
      "Jane" -> 2312323 // ("Jane", 2312323)
    )
}