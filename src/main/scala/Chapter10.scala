/**
  * Created by Christopher on 5/15/2017.
  */

import com.sun.org.apache.xpath.internal.operations
import com.sun.org.apache.xpath.internal.operations.And
import doodle.core.Point._
import doodle.core.PathElement._
import doodle.core.Color
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
        List(branch(turn(45.degrees),forward(stepSize),noop),branch(turn((-45).degrees),forward(stepSize),noop))
      case other => List(other)

    }
  }

  def rewrite(instructions: List[Instruction],rule :Instruction => List[Instruction]):List[Instruction] = {
    instructions.flatMap(a=>rule(a))
  }

/*  def iterate(steps:Int,seed: List[Instruction],rule :Instruction => List[Instruction]):List[Instruction] ={

    def loop(steps:Int,seed: List[Instruction],rule :Instruction => List[Instruction],counter: Int):List[Instruction] ={
      counter match {
        case 0 => Nil
        case n => seed.flatMap(a => loop(steps,a,rule,n-1))
      }
      }
    loop(steps,seed,rule,steps)
    }*/

  val testDraw = iterate(5,seed,rule)

  /*

    val circle:Random[Point] =
      angle flatMap { a =>
        radius map { r =>
          Point.polar(r,a):Point

        }:Random[Point]
      }*/


}
