package com.rockthejvm

object ObjectOrientation extends App {
  // Java equivalent: public static void main(String[] args)

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

  // constructor arguments are NOT fields
  // so aDog.name is not possible!!

  class Bird(val name: String) extends Animal
  val aBird = new Bird("Birdie")

  // here aBird.name is possible because it has been made a field by adding val in front of it
  println(aBird.name)

  // subtype polymorphism
  val aDeclaredAnimal: Animal = new Bird("Henk")
  aDeclaredAnimal.eat() // the most derived method will be called at runtime, dus als bird een eat methode heeft, wordt die methode uitgevoerd

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true // all fields and methodes are public by default, you can restrict that by using "private" or "protected" in front of "val"
    // private would mean only this class can access it
    // protected would mean this class and all its descendants have acces to it
    def walk(): Unit
  }

  // "interface" = ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait SomeOtherTrait{}

  // dus trait is vergelijkbaar met een interface in Java en een abstracte klasse met een abstracte klasse in Java
  // net als bij Java kan een class meerdere traits (interfaces) implementeren, maar maar 1 abstracte klasse overerven
  // single-class inheritance, multi-trait "mixing"
  class Crocodile extends Animal with Carnivore with SomeOtherTrait {
    // net als in Java moet in een class alle abstracte methodes worden geïmplementeerd, of de class zelf moet abstract gemaakt worden
    override def eat(animal: Animal): Unit = println(s"$animal: om nom nom")
  }

  val crocie = new Crocodile
  crocie.eat(aBird)
  crocie eat aBird // infix notation = object method argument, only available with methods with ONE argument

  // operators in Scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) // equivalent to the one above

  // anonymous classes
  // in tegenstelling tot Java, kan in Scala een interface of abstracte klasse wel geinstantieerd worden. er moet dan wel een implementatie voor de abstracte methoden/values komen
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur.... OM NOM NOM...")
  }

  // singleton object
  object MySingleton { // the only instance of the MySingleton type, dus een object is EEN instantie, waar een klasse meerdere instanties kan hebben
    val mySpecialValue = 395473
    def mySpecialMethod(): Int = 3948
    def apply(x: Int): Int = {  //apply is a special method
      println(21)
      return x + 1
    }
    def apply(y: String): Unit = println(y)
  }

  MySingleton.mySpecialMethod()
  MySingleton.apply(6)
  MySingleton(6) // equivalent to the one above
  MySingleton("cool, je kan de apply() methode overloaden")
  // MySingleton("kan je de verschillende apply() methoden ook combineren?", 5 )
  // het lijkt erop dat je de verschillende apply() methoden niet tegelijk kan aanroepen

  object Animal { // je kan een object en class met dezelfde naam hebben, dan zijn het "companions" - companion object (this can also be applied to traits)
    // companions can access other's private fields/methods
    // singleton Animal (dit object Animal()) and instances of Animal (de class Animal()) are different things
    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely // "static" fields/methods
  // dus als ik het goed begrijp gebruik je singleton objects voor het maken van "static" fields and methods

  /*
    case classes = lightweight data structures with some boilerplate
    the compiler gegenereerd automatisch de volgende code voor case classes:
    - sensible equals and hash code
    - sensible and quick serialization
    - companion with apply (voorbeeld below)
    - pattern matching
   */
  case class Person(name: String, age: Int)

  // a case class automatically generates an apply function so you can omit "new" here (so you can call "Person("name", age)" instead of "new Person("name", age)"
  val bob = Person("bob", 21)

  // exceptions
  try {
    // code that can throw an exception
    val x: String = null
    x.length
  } catch {
    case e: Exception => "some faulty error message"
  } finally println("THE ERROR IS HANDLED...")

  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type
  val aList: List[Int] = List(1, 2, 3) // == List.apply(1, 2, 3)
  val first = aList.head
  val rest = aList.tail
  val aStringList = List("hello", "Scala")

  // Point #1: in Scala we usually operate with IMMUTABLE values/objects
  // Any modification to an object must return ANOTHER object
  /*
      Benefits:
      1) works miracles in multithreaded/distributed env
      2) helps making sense of the code ("reasoning about")
   */
  val reverseList = aList.reverse // returns a NEW list

  //Point #2: Scala is the closest to the Object-Oriented ideal
  //scala is marketed as a mix between Object-Oriented and functional programming
}
