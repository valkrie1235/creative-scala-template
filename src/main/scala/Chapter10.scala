/**
  * Created by Christopher on 5/15/2017.
  */

import com.sun.org.apache.xpath.internal.operations
import com.sun.org.apache.xpath.internal.operations.And
import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._
import doodle.turtle._
import doodle.turtle.Instruction._

object Chapter10 {


  //Had to set sides to be a double otherwise you run into rounding issues with the angle
  def polygon(sides: Double, sideLength: Double): Image = {
    val angle = (360/sides).degrees

    def loop(sides: Double, sideLength:Double, counter: Int): List[Instruction] = {
      counter match {
        case 0 => Nil
        case n => forward(sideLength) :: turn(angle) :: loop(sides,sideLength, n - 1)
      }
    }
    Turtle.draw(loop(sides,sideLength,sides.toInt))
  }

  def spiralCircle(maxSize: Double, angleIncrement: Angle, numTurns:Int): Image = {
    val sideLengthIncrement = maxSize/(numTurns*4)
    print(sideLengthIncrement)

    def loop(maxSize: Double, angleIncrement: Angle, numTurns:Int, counter: Int): List[Instruction] = {
      counter match {
        case 0 => Nil
        case n => forward(sideLengthIncrement*n) :: turn(90.degrees+angleIncrement) :: loop(maxSize,angleIncrement,numTurns, n - 1)
      }
    }
    Turtle.draw(loop(maxSize,angleIncrement,numTurns,numTurns*4))
  }

  def double[A](list:List[A]):List[A] = {
    list.flatMap(a => List(a,a))
  }

  def nothing[A](list:List[A]):List[A] = {
    list.flatMap(a => Nil)
  }

  val stepSize = 100
  val seed = List(forward(100),NoOp)

  def rule(i: Instruction): List[Instruction] = {
    i match {
      case Forward(_)=>List(forward(stepSize),forward(stepSize))
      case NoOp =>
        List(branch(turn(45.degrees),forward(stepSize),NoOp),branch(turn((-45).degrees),forward(stepSize),NoOp))
      case other => List(other)

    }
  }

  def rewrite(instructions: List[Instruction],rule :Instruction => List[Instruction]):List[Instruction] = {

    instructions match {
      case List(Branch(_))=> List(branch(instructions.flatMap(a=>rule(a)):_*))
      case other => other.flatMap(a => rule(a))
    }

  }

 def iterate(steps:Int,seed: List[Instruction],rule :Instruction => List[Instruction]):List[Instruction] = {
    steps match {
      case 0 => seed
      case n => iterate(n-1,rewrite(seed,rule),rule)
  }
 }

  val testDraw = iterate(3,seed,rule)

  /*

    val circle:Random[Point] =
      angle flatMap { a =>
        radius map { r =>
          Point.polar(r,a):Point

        }:Random[Point]
      }*/


}
