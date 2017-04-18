/**
  * Created by Christopher on 4/18/2017.
  */
import doodle.core.Color
import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

object Chapter7 {


  //Excercise:Stacking boxes
  def stackingBoxes(boxCount: Int, boxDim: Double, boxColor: Color): Image = {
    val aBox = Image.rectangle(boxDim,boxDim).fillColor(boxColor)
    boxCount match{
      case 0 => Image.empty
      case n => aBox above stackingBoxes(n-1,boxDim,boxColor)
    }
  }

  /*Excercise: Guess the result
    */

  object guessTheResult {
    val three = 1 match {
      case n => n+1
      case 1 => 1000
    }

    println(three)

    val four = 1 match {
      case a => a
      case b => b+1
      case c=> c*2
    }

    println(four)

    val five = 1 match {
      case 2 => "yes"
      case 3 => "no"
      case 4 => "panda"
    }

    println(five)
  }

  def cross(count: Int): Image = {
    val aShape = Image.circle(10)
    count match {
      case 0 => aShape
      case n => aShape beside (aShape above cross(n-1) above aShape) beside aShape
    }
  }


  def chessboard(count: Int): Image = {
    val squareDim = 20
    val redSquare = Image.rectangle(squareDim, squareDim).fillColor(Color.red)
    val blackSquare = Image.rectangle(squareDim, squareDim).fillColor(Color.black)
    val fourSquare = (redSquare beside blackSquare) above (blackSquare beside redSquare)

    count match {
      case 0 => fourSquare
      case n => chessboard(n - 1) beside chessboard(n - 1)  above (chessboard(n - 1)  beside chessboard(n - 1) )
    }

  }

  def sierpinski(count: Int): Image ={
    val triangleHeight = 40
    val triangleWidth = 40
    val baseTriangle =  Image.triangle(triangleWidth,triangleHeight) //above (Image.triangle(triangleWidth,triangleHeight)  beside Image.triangle(triangleWidth,triangleHeight))

    count match{
      case 0 => baseTriangle
      case n => sierpinski(n-1) above (sierpinski(n-1) beside sierpinski(n-1))
    }
  }


  /****Excercise "silly examples
    * First one yes, should work
    * second one won't, it will return 2^n*n
    *
    */

  def gradientBoxes(boxCount: Int, boxDim: Double, boxColor: Color): Image = {
    val aBox = Image.rectangle(boxDim,boxDim)
      .fillColor(boxColor)
      .lineWidth(boxDim/8)
      .lineColor(boxColor.spin(5.degrees))
    boxCount match{
      case 0 => Image.empty
      case n => aBox beside  gradientBoxes(n-1,boxDim,boxColor.spin(10.degrees))
    }
  }

  def concentricCircles(count: Int, Width: Double):Image ={
    val aCircle = Image.circle(Width)
    count match {
      case 0 => aCircle
      case n => aCircle on concentricCircles(n-1,Width+10)
    }
  }

  def withFeeling(count: Int, Width: Double, startingColor: Color, degreeShift:Int):Image ={
    val aCircle = Image.circle(Width).lineColor(startingColor)
    count match {
      case 0 => aCircle
      case n => aCircle on withFeeling(n-1,Width+10,startingColor.spin(degreeShift.degrees),degreeShift)
    }
  }


  def chessboardNested(count: Int): Image = {
    val squareDim = 20
    val redSquare = Image.rectangle(squareDim, squareDim).fillColor(Color.red)
    val blackSquare = Image.rectangle(squareDim, squareDim).fillColor(Color.black)
    val fourSquare = (redSquare beside blackSquare) above (blackSquare beside redSquare)
    println("creating chessboard unit")

    def loop(count: Int): Image = {
      count match {
        case 0 => fourSquare
        case n => loop(n - 1) beside loop(n - 1) above (loop(n - 1) beside loop(n - 1))
      }
    }

    loop(count)
  }

  def stackingBoxesNested(boxCount: Int, boxDim: Double, boxColor: Color): Image = {
    val aBox = Image.rectangle(boxDim, boxDim).fillColor(boxColor)
    println("creating atomic box unit")

    def loop(boxCount: Int, boxDim: Double, boxColor: Color): Image = {
      boxCount match {
        case 0 => Image.empty
        case n => aBox above loop(n - 1, boxDim, boxColor)
      }
    }
    loop(boxCount,boxDim,boxColor)
  }
}
