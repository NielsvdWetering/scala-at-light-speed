package com.rockthejvm

object FunctionalProgramming extends App {

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg:  Int): Int = arg + 1
  }

  println(simpleIncrementer(24))


  // syntax sugars
  val doubler: Function[Int, Int] = (x: Int) => 2 * x   // je kan "Function[Int, Int]" vervangen/verkorten met "Int => Int", en als je echt kort wilt doen kan je de return type zelfs weglaten
  val doubler2: Int => Int = (x: Int) => 2 * x
  val doubler3 = (x:Int) => 2 * x
  /*
    equivalent to the much longer:

    new Function1[Int, Int] {
      override def apply(x: Int) = 2 * x
      }
   */
  println(doubler(2))

  // higher-order functions: takes functions as args/return functions as result
  val aMappedList = List(1,2,3).map(x => x + 1) // de map function is called a Higher-Order Function
  println(aMappedList)

  val aFlatMappedList = List(1,2,3).flatMap(x => List(x, 2 * x))
  print("aFlatMappedList(1,2,3): ")
  println(aFlatMappedList)

  // an alternative way to write the same code (as aFlatMappedList)
  val aAlternativeWay = List(1,2,3).flatMap { x =>
    List(x, 2 * x)
  }

  val aFilteredList = List(1,2,3,4,5,6,7).filter(x => x < 6 && x >= 2)
  println(aFilteredList)

  val aFilteredListWithEvenShorterNotation = List(1,2,3,4,5,6,7,8,9).filter(_ >= 2) // equivalent to "x => x >= 2"
  println(aFilteredListWithEvenShorterNotation)

  // all pairs between the numbers 1,2,3 and the letters 'a','b',ç'
  val allPairsInAList = List('1','2','3').flatMap(number => List('a','b','c').flatMap(character => List(number, character)))
  println(allPairsInAList)

  val allPairs = List(1,2,3).flatMap(number => List('a','b','c').map(letter => s"$number$letter"))
  println(allPairs)

  // for comprehensions
  val alternativePairs = for { // "for" stands for for-comprehension, so it is not a for-loop
    number <- List(1,2,3)
    letter <- List('a','b','c')
  } yield s"$number$letter"
  // alternativePairs() and allPairs() are identical
  println(s"alternative pairs: $alternativePairs")

  /*
    Collections
   */

  // lists, the fundamental collections of functional programming
  val aList = List(1,2,3,4,5)
  val firstElement = aList.head
  val theRestOfTheList = aList.tail
  val aPrependedList = 0 :: aList // returns List(0,1,2,3,4,5)
  val anExtendedList = 0 +: aList :+ 6 // returns List(0,1,2,3,4,5,6)

  // sequences
  // the main characteristic of a sequence is that you can acces a value at a given index
  val aSequence: Seq[Int] = Seq(1,2,3) // Seq.apply(1,2,3)
  val accesedElement = aSequence(1) // returns the element at index 1, so in this case 2

  // vectors: fast sequence implementation
  // has the same methods as List and Sequence
  val aVector = Vector(1,2,3,4,5)

  // sets = no duplicates and geen vaste volgorde
  val aSet = Set(1,2,3,4,1,2,3) // returns Set(1, 2, 3, 4)
  println(s"aSet of Set(1,2,3,4,1,2,3): $aSet")

  val setHas5 = aSet.contains(5) // false
  val anAddedSet = aSet + 5 // Set(1,2,3,4,5)
  val aRemovedSet = aSet - 3 // Set(1,2,4)

  // ranges
  // usefull for "itteration"
  val aRange = 1 to 10000
  val twoByTwo = aRange.map(2 * _).toList // returns all even numbers from 1 to 2000
  println(twoByTwo)
  val allOddNumbers = aRange.filter(_ % 2 != 0)
  println(allOddNumbers)

  // tuples = groups of values under the same value
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps
  val aPhonebook: Map[String, Int] = Map(
    ("Daniel", 34957329),
    "Henk" -> 39483427
  )
}
