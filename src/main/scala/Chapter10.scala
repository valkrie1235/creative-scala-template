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
import doodle.core.PathElement.{lineTo, moveTo}
import doodle.core.Point.polar
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

  val stepSize = 10
  val seed = List(forward(100),NoOp)
  val seedEmpty = List(NoOp)

  def rule(i: Instruction): List[Instruction] = {
    i match {
      case Forward(_)=>List(forward(stepSize),forward(stepSize))
      case NoOp =>
        List(branch(turn(45.degrees),forward(stepSize),NoOp),branch(turn((-45).degrees),forward(stepSize),NoOp))
      case other => List(other)

    }
  }

  def ruleKoch(i: Instruction): List[Instruction] = {
    i match {
      case Forward(_)=>List(forward(stepSize),turn(45.degrees),forward(stepSize),turn((-90).degrees),forward(stepSize),turn(45.degrees),forward(stepSize))
      case other => List(other)

    }
  }

  def ruleLeaf(i: Instruction): List[Instruction] = {
    i match {
      case Forward(_)=>List(forward(stepSize),branch(turn(30.degrees),forward(stepSize)),branch(turn((-30).degrees),forward(stepSize)),forward(stepSize))
      case other => List(other)

    }
  }

  def ruleCrosses(i: Instruction): List[Instruction] = {
    i match {
      case Forward(_) => List(forward(stepSize), forward(stepSize))
      case NoOp =>
        List(branch(forward(stepSize), NoOp), branch(turn(90.degrees), forward(stepSize), NoOp), branch(turn(180.degrees), forward(stepSize), NoOp), branch(turn((-90).degrees), forward(stepSize), NoOp))
      case other => List(other)

    }
  }


  /*a little pattern for defining these things
  algebraic data types,

  */

  def rewrite(instructions: List[Instruction],rule :Instruction => List[Instruction]):List[Instruction] = {

    instructions.flatMap {
      x => x match {
        case Branch(x) => List(branch(rewrite(x, rule): _*))
        case other => rule(other)
      }
    }
   }

 def iterate(steps:Int,seed: List[Instruction],rule :Instruction => List[Instruction]):List[Instruction] = {
    steps match {
      case 0 => seed
      case n => iterate(n-1,rewrite(seed,rule),rule)
  }
 }

  def polygonFlat(sides: Int, sideLength: Double): Image = {
    val angle = (360/sides).degrees

    Turtle.draw((0 until sides).toList.flatMap{x => List(turn(angle),forward(sideLength))})

  }


  def spiralCircleFlat(maxSize: Double, angleIncrement: Angle, numTurns:Int): Image = {
    val sideLengthIncrement = maxSize/(numTurns*4)
    print(sideLengthIncrement)

    Turtle.draw (
      (0 until numTurns).toList.flatMap(x =>
        List(forward(sideLengthIncrement*x),turn(90.degrees+angleIncrement) )
      )
    )

  }


  def nautilus(maxSize: Double, numTurns:Int): Image = {
    val sideLengthIncrement = maxSize/numTurns
    val angleIncrement = Angle.one/numTurns


    def longSide(iter:Int):Double = math.sqrt(
      math.pow(sideLengthIncrement*iter,2)
        + math.pow(sideLengthIncrement*(iter+1),2)
        - 2*(sideLengthIncrement*iter)*(sideLengthIncrement*(iter+1)*math.cos(math.toRadians(360/numTurns))
        )
    )

    def longAngle(iter:Int):Double = math.asin(math.sin(math.toRadians(Angle.one.toDegrees/numTurns))/longSide(iter)*sideLengthIncrement*iter).toDegrees

    Turtle.draw (
      (0 until numTurns).toList.flatMap(x =>
        List(
          branch(
            turn(angleIncrement*x),forward(sideLengthIncrement*x),turn(longAngle(x).degrees),forward(longSide(x))
          )
        )
      )
      )
  }

  val testDraw = Turtle.draw(iterate(3,seed,rule))
  val testDrawKoch = Turtle.draw(iterate(5,seed,ruleKoch))
  val testDrawLeaf = Turtle.draw(iterate(5,seed,ruleLeaf))
  val testDrawCross = Turtle.draw(iterate(5,seedEmpty,ruleCrosses))


}
