/**
  * Created by Christopher on 4/18/2017.
  */

import doodle.core.Color
import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._
object Chapter8 {

  /*def rose(angle:Angle) =
    Point.polar((angle*7).cos * 200, angle)
*/

  val rose = (x: Angle) => Point.polar((x * 7).cos * 200, x)

  def concentricShapes(count: Int, singleShape: Double => Image): Image = {
    count match {
      case 0 => Image.empty
      case n => singleShape(n) on concentricShapes(n - 1, singleShape)
    }
  }

  //Shapes
  def circle(n: Double) = Image.circle(n * 5).lineWidth(2.5)

  def triangle(n: Double) = Image.triangle(n * 10, n * 10).lineWidth(2.5)

  def square(n: Double) = Image.rectangle(n * 10, n * 10).lineWidth(2.5)

  //Color Functions
  def gradient(n: Double,initialColor:Color) = initialColor.spin(n.degrees * 90)

  def fade(n: Double,initialColor:Color) = initialColor.lighten(1.0.normalized).darken((n / 20.0).normalized)

  //size


  def colored(shape: Double => Image, color: (Double,Color) => Color): (Double,Color) => Image = (n: Double, initialColor:Color) => shape(n).lineColor(color(n,initialColor))

//  val test = concentricShapes(10, colored(circle(_), gradient(_))) beside concentricShapes(10, colored(triangle(_), fade(_))) beside concentricShapes(10, colored(square(_), gradient(_)))

//  val test2 = concentricShapes(100, colored(circle(_), fade(_)))


  object flower {

    def rose = (x: Angle, numPetals: Int, maxRadius: Double) => Point.polar((x * numPetals).cos * maxRadius, x)

    def spiral = (x: Angle, dim: Int,maxRadius:Double) => Point.cartesian(maxRadius * (x * dim).sin, maxRadius * x.sin)

    def flower(start: Angle, samples: Int, shapeSize: Int, numPetals: Int, maxRadius: Double, initialColor: Color, pEquation: (Angle, Int, Double) => Point, shape: (Double,Color) => Image): Image = {

      val step = Angle.one / samples

      def loop(count: Int): Image = {
        val angle = step * count
        val currentSize = shapeSize * angle.toRadians.toDouble
        count match {
          case 0 => Image.empty
          case n => shape(currentSize,initialColor).at(pEquation(angle, numPetals, maxRadius).toVec) on loop(n - 1)
        }
      }

      /*     def tailLoop(count: Int, emergingShape:Image): Image = {
        val angle = step * count
        val currentSize = shapeSize * angle.toRadians.toDouble
        count match {
          case 0 => emergingShape
          case n => tailLoop(n-1,shape(currentSize).at(pEquation(angle,numPetals).toVec) on tailLoop(n-1,emergingShape))
        }*/

      loop(samples)
    }

      val flowerTest = flower(0.degrees, 200, 1, 5,200, Color.purple, rose (_, _,_) , colored(circle(_), gradient(_,_)))
      val flowerTest4 = flower(25.degrees, 200, 1, 8,400, Color.blue, rose(_, _,_), colored(circle(_), fade(_,_)))


    val finalFlower = flowerTest on flowerTest4
    // def flower = flowerTest on flowerTest4


  }

}

