/**
  * Created by am_dev on 5/5/17.
  */

import doodle.core.Color
import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._
import cats.Monoid
import cats.implicits._
import doodle.random._

object Class5 {


  def circle(x:Double,y:Double,radius:Double) = Image.circle(radius).at(x,y)

  val angle:Random[Angle] = Random.int(0,360).map(x=>x.degrees)

  val radius:Random[Double] = Random.double

  //val scale: Double => (Point => Point) =


    //Random[Angle] ??? Random[Double] => Random[Point]
  val circle:Random[Point] =
    angle flatMap { a =>
      radius map { r =>
        Point.polar(r,a):Point

       }:Random[Point]
    }

  //Random[List[Image]]]

  /*
  def randomPoint = Random.int.map(x => x+1)

  }*/

/*  def randomPoint(maxRadius:Double):Point = {
    Point.Cartesian(Random.double,Random.double)
  }*/

/*  def listOfPoints(numPoints: Int): List[Random.int] ={
    (0 until numPoints).map(x => Random.int).toList
  }*/

}
