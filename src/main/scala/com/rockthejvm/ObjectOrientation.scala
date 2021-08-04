package com.rockthejvm

object ObjectOrientation extends App {

  // extending App gives us 
  // java equivalent: public static void main(String[] args)

  // class and instance
  class Animal {
    // define fields
    val age: Int = 0

    // define methods
    def eat() = println("I'm eating")
  }

  val anAnimal = new Animal

  // inheritance
  class Dog(name: String) extends Animal // constructor definition
  val aDog = new Dog("Lassie")

  // constructor argument are NOT fields
  // not possible to aDog.name

  class DogVal(val name: String) extends Animal // use val to promote a constructor argument to a field
  val aDogVal = new DogVal("LassieVal")
  // aDogVal.name exists println(aDogVal.name);

  //subtype polymorphism
  val aDeclaredAnimal: Animal  = new Dog("Hachi")
  aDeclaredAnimal.eat() // called from the Dog class. The most derived method will be called at runtime

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true // by default public, can restict by using private/protected
    def walk(): Unit // leave class definition as is
  }

  // "interface" = ultimate abstract type
  trait Carnivore { // use traits to denote characteristics of objects that we can later implement in concrete classe
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(though: String): Unit // ?! is a valid method name
  }

  // single-class inheritance, multi-trait "mixing"
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("I am eating you")

    override def ?!(though: String): Unit = println(s"I was thinking: $though")
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog // infix notation = <object> <method> <argument>, only available to methods with one argument
  aCroc ?! "What if we could fly?"

  // operators in Scala are actually methods
  val basicMath = 1 + 2 // + is a method
  val anotherBasicMath = 1.+(2)

  // anonymous Classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I'm a dinossaur, I can eat pretty much anything")
  }
  /**
   * // What you tell the compiler is this -> 
   class Carnivore_Anonymouse_35728 extends Carnivore {
     override def eat(animal: Animal): Unit = println("I'm a dinossaur, I can eat pretty much anything")
   } 
   val dinossaur = new Carnivore_Anonymouse_35728
  **/

  object MySingleton { // the only instance of the MySingleton type
    val mySpecialValue = 41292
    def mySpecialMethod(): Int = mySpecialValue
    def apply(x: Int): Int = x + 1 // presence of apply allows instances of that class to call the apply method lyke MySingleton(65) ~= MySingleton.apply(65)
  }

  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  MySingleton(65) // equivalent to MySingleton.apply(65)

  object Animal { // class and object Animal are companions. this is a companion object
    // companions can access each other's private fields/methods
    // singleton Animal and instances of Animal are different things
    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely // "static" fields

  /**
    * case classes = lightweight data structures with some boilerplate
    * - sensible equals and hash code
    * - serialization
    * - companion with apply
    * - pattern matching
    */
  case class Person(name: String, age: Int)

  // may be constructed without the "new"
  val bob = Person("Bob", 54) // Person.apply("Bob", 54)


  // exceptions
  try {
    // code that can throw
      val x: String = null
      x.length // will throw an error
  } catch {
    case e: Exception => "some faulty error message"
  } finally {
    // execute some code no matter what - closing connections, files, etc
  }

 // generics
 abstract class MyList[T] {  // applicable for any type defined as T
  def head: T
  def tail: MyList[T]
 }

 // using a generic with a concrete type
 val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
 val first = aList.head // compiler knows this val is of type Int
 val rest = aList.tail
 val aStringList = List("hello", "Scala")
 val firstString = aStringList.head // string


 // Point #1: in Scala we usually operate with IMMUTABLE values/objects
 // Any modifications to an object must return ANOTHER object
 /**
   * Benefits:
   * 1) works miracles in multithreaded/distributed env
   * 2) helps making sense of the code ("reasoning about")
   */
 val reversedList = aList.reverse // returns a NEW list

 // Point #2: Scala is closest to the OO ideal


}
