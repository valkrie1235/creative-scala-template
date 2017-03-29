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
    val WidthofCircle= (threeCircles on  circle(30)).draw

    //EvilEye
    val outerIris = circle(100) fillColor(Color.darkBlue)
    val innerIris = circle(40) fillColor(Color.cornflowerBlue)
    val pupil = circle(30) fillColor(Color.black)
    val sclera = circle(60) fillColor(Color.white)
    val evilEye = (pupil on innerIris on sclera on outerIris)
    evilEye.draw


  }

}
