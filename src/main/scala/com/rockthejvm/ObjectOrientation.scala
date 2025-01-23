package com.rockthejvm

object ObjectOrientation extends App {

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

  
}
