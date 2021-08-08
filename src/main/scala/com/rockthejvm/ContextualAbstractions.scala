package com.rockthejvm

object ContextualAbstractions {
  /*
    1 - context parameters/arguments
  */
  val aList = List(2,1,3,4)
  val anOrderedList = aList.sorted // contextual argument: (descendingOrdering)

  // Ordering
  given descendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _) // (a, b) => a > b

  // analogous to an implicit val

  trait Combinator[A] { // monoid
    def combine(x: A, y: A): A
  }


  def combineAll[A](list: List[A])(using combinator: Combinator[A]): A =  // using
    list.reduce((a,b) => combinator.combine(a,b))
    
  // combineAll(List(1,2,3,4))

  given intCombinator: Combinator[Int] = new Combinator[Int] {
    override def combine(x: Int, y: Int) = x + y
  }

  val theSum = combineAll(aList)

  // import yourpackage.given - imports all given of that package
  /*
    The compiler looks for 'Given' in the following places
    - local scope
    - imported scope
    - the companions of all the types involved in the call
      - in our case -> Companion of list and the companion of Int
  */

  // context bounds
  def combineAll_v2[A](List: List[A])(using Combinator[A]): A = ???
  def combineAll_v3[A : Combinator](List: List[A]): A = ??? // v2 === v3

  /*
    where context args are useful
    - type classes
    - dependency injection
    - context-dependent functionality
    - type-level programming
    - 
  */

  /*
    2 - extension methods
  */

  case class Person(name: String) {
    def greet(): String = s"Hi, my name is $name, and I love Scala!"
  }

  extension (string: String)
    def greet(): String = new Person(string).greet()

  val danielsGreeting = "Lucas".greet() // "Type enrichment" or "pimping"

  // POWER
  extension [A] (list: List[A])
    def combineAllValues(using combinator: Combinator[A]): A =
      list.reduce(combinator.combine)


  def theSum_v2 = aList.combineAllValues


  def main(args: Array[String]): Unit = {
    println(anOrderedList)
    println(theSum)
    println(theSum_v2)
  }
}