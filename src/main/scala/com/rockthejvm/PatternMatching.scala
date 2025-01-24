package com.rockthejvm

object PatternMatching extends App {

  // switch expression
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th" // default case
  }
  println(order)

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 21)

  val personGreeting = bob match { // pattern matching is vooral beschikbaar voor case classes, het kan ook bij "normale" classes maar dan moet je een hoop "magic" achter de schermen gaan doen (erg advanced)
    case Person(n, a) => s"Hi, my name is $n and I am $a years old." // de case checked of bob dezelfde structure heeft
    case _ => "Something else"
  }
  println(personGreeting)

  // deconstructing tuples
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre"
    case _ => "I don't know what you are talking about"
  }

  // decomposing lists
  val aList = List(1,2,3)
  val listDescription = aList match {
    case List(_, 2, _) => "List containing 2 on its second position"
    case _ => "unknown list" // throws an error if no default case is present and no cases are matching
  }

  // pattern matching will try all cases in sequence, just like Java switch case
  


}
