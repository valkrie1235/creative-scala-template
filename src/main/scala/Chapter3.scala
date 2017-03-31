/**
  * Created by am_dev on 3/29/17.
  */


import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

object Chapter3 {
  def main(args: Array[String]): Unit = {

    //Making some random shapes
    //type rectangle(100,50)
    val circle1 =  circle(1)
    val circle10 = circle(10)
    val circle100 = circle(100)
    val goRoundInCircles = (circle1 above circle10 above circle100)
    //goRoundInCircles.draw
    // println(goRoundInCircles.getClass.toString)

    //Width of a Circle
    val threeCircles = (circle(10) beside circle(10) beside circle(10))
    val WidthofCircle= (threeCircles on  circle(30))
    WidthofCircle.draw

    //EvilEye
    val outerIris = circle(100) fillColor(Color.darkBlue)
    val innerIris = circle(40) fillColor(Color.cornflowerBlue)
    val pupil = circle(30) fillColor(Color.black)
    val sclera = circle(60) fillColor(Color.white)
    val evilEye = (pupil on innerIris on sclera on outerIris)
    //evilEye.draw

    //color pyramid
    val triangle1 = triangle(60,60) fillColor(Color.darkSlateBlue)
    val triangle2 = triangle(60,60) fillColor(Color.darkSlateBlue darken 0.2.normalized)
    val triangle3 = triangle(60,60) fillColor(Color.darkSlateBlue lighten 0.2.normalized)
    val colorPyramid = triangle1 above (triangle2 beside triangle3)
    //colorPyramid.draw

    //target
    val eightRing = circle(40) fillColor(Color.red)
    val nineRing = circle(25) fillColor(Color.white)
    val tenRing = circle(10) fillColor(Color.red)
    val target = (tenRing on nineRing on eightRing)

    val ground = rectangle(100,30) fillColor(Color.green)
    val stand = rectangle(25,10) fillColor(Color.brown)
    val pole = rectangle(10,25) fillColor(Color.white)

    val targetStand = (ground below stand below pole below target)
    //targetStand.draw

  }

}
