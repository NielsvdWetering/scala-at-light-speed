package com.rockthejvm

import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Advanced extends App {

  // lazy evaluation
  // wordt niet geevalueerd totdat het voor het eerst gebruikt wordt
  lazy val aLazyValue = 2 // aLazyValue word pas 2 als het voor de eerste keer gebruikt gaat worden
  lazy val lazyVallueWithSideEffect = {
    println("I am so very lazy!") // omdat dit code-block in een lazy val staat word deze niet uitgeprint, want de value word niet geevalueerd totdat de 43 is toegewezen, maar dan is het al voorbij de println
    43
  }

  val eagerValue = lazyVallueWithSideEffect + 1 // nu wordt de println in lazyValueWithSideEffect wel uitgevoerd omdat er al gebruik van gemaakt is
  // useful in infinite collections

  // "pseudo-collections": Option, Try
  def methodWhichCanReturnNull(): String = "Hello Scala"
  val anOption = Option(methodWhichCanReturnNull()) // Some("Hello Scala")
  // option = "collection" which cointains at most one element, dus volgens mij vergelijkbaar met Java Optional

  // omdat hij met een option een verschillende signature teruggeeft kan je dus met een match checken of het een string of een null value is
  val stringProcessing = anOption match {
    case Some(string) => s"a valid string: $string"
    case None => "that was a null value"
  }

  def methodWhichCanThrowException(): String = throw new RuntimeException
  val aTry = Try(methodWhichCanThrowException())
  // a try = "collection" with either a value if the code went well, or an exception if the code threw one

  val anotherStringProcessing = aTry match { // "Success" and "Failure" are subtypes from Try
    case Success(string) => s"everything went well! $string"
    case Failure(exception) =>
  }

  /**
   * Evaluate something on another thread
   * (asynchronous programming)
   */
  val aFuture = Future {
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value.")
    67
  }

  // future is a "collection" which contains a value when it's evaluated
  // future is composable with map, flatmap and filter


  /**
   * Implicits basics
   */

  // #1: implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImplicitInt = 21
  println(aMethodWithImplicitArgs) // aMethodWhithImplicitARgs(myImplicitInt)

  // #2: implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven() = n % 2 == 0
  }

  println(25.isEven()) // new MyRichInteger(25).isEven()
  // use this carefully
}
