/**
  * Created by Christopher on 5/1/2017.
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

object Chapter9 {

  def curvePath(point: Point):PathElement ={
    curveTo(point.rotate(340.degrees),point.rotate(320.degrees),point)
  }

  val triangle = List(
    moveTo(polar(100.0,0.degrees)),
    lineTo(polar(100.0,120.degrees)),
    lineTo(polar(100.0,240.degrees))
  )

  val square = List(
    moveTo(polar(100.0,45.degrees)),
    lineTo(polar(100.0,135.degrees)),
    lineTo(polar(100.0,225.degrees)),
    lineTo(polar(100.0,315.degrees))
  )

  val pentagon = List(
    moveTo(polar(100.0,72.degrees)),
    lineTo(polar(100.0,144.degrees)),
    lineTo(polar(100.0,216.degrees)),
    lineTo(polar(100.0,288.degrees)),
    lineTo(polar(100.0,360.degrees))
      )

  val triangleCurved = List(
    moveTo(polar(100.0,0.degrees)),
    curvePath(polar(100.0,120.degrees)),
    curvePath(polar(100.0,240.degrees)),
    curvePath(polar(100.0,360.degrees))

  )

  val squareCurved = List(
    moveTo(polar(100.0,45.degrees)),
    curvePath(polar(100.0,135.degrees)),
    curvePath(polar(100.0,225.degrees)),
    curvePath(polar(100.0,315.degrees)),
    curvePath(polar(100.0,45.degrees))

  )

  val pentagonCurved = List(
    moveTo(polar(100.0,72.degrees)),
    curvePath(polar(100.0,144.degrees)),
    curvePath(polar(100.0,216.degrees)),
    curvePath(polar(100.0,288.degrees)),
    curvePath(polar(100.0,360.degrees)),
    curvePath(polar(100.0,72.degrees))
  )

  val closedPolygons = (closedPath(triangle) beside closedPath(square) beside closedPath(pentagon))
  val curvedPolygons = openPath(triangleCurved) beside openPath(squareCurved) beside openPath(pentagonCurved)


  def ones(length:Int):List[Int] ={
    length match{
      case 0 =>Nil
      case n => 1 :: ones(n-1)
    }
  }

  def descending(startingVal:Int):List[Int] ={
    startingVal match{
      case 0 =>Nil
      case n =>  startingVal :: descending(n-1)
    }
  }

  def ascending(endVal:Int):List[Int] ={

    def loop(value:Int, counter:Int):List[Int] = {
      counter match {
        case 0 => Nil
        case n => value :: loop(value+1,counter-1)
      }
    }
    loop(1,endVal)
  }

  def fill[A](length:Int,a: A):List[A] ={
    length match {
      case 0 => Nil
      case n => a :: fill(n-1,a)
    }

  }

  def double(list:List[Int]):List[Int] ={
    list.map(x=>x*2)
  }

  def product(list:List[Int]):Int ={
    list match {
      case Nil => 1
      case hd :: tl => hd * product(tl)
    }
  }

  def contains[A](list: List[A], a:A):Boolean = {
    list match{
      case Nil => false
      case hd :: tl => (hd == a) || contains(tl,a)
    }
  }

/*  def reverse[A](list: List[A]):List[A] = {
    list match{
      case Nil => Nil
      case hd :: tl =>
    }
  }*/

    def polygon(sides: Int,initialAngle: Angle): Image = {
      val increment = 360 / sides

      def loop(sides: Int, initialAngle:Angle, counter: Int): List[PathElement] = {
        counter match {
          case 0 => Nil
          case n => lineTo(polar(100.0, initialAngle+(increment*counter).degrees)) :: loop(sides,initialAngle, n - 1)
        }
      }
      closedPath(moveTo(polar(100.0,initialAngle)) :: loop(sides,initialAngle,sides))
    }

  object ranges {

    def ones(length:Int):List[Int] ={
      (0 until length).map(x=>1).toList
    }

    def descending(startingVal:Int):List[Int] ={
      (0 until startingVal).map(x=>startingVal-x).toList
    }


    def ascending(startingVal:Int):List[Int] ={
      (startingVal until 0 by -1).map(x=>startingVal-(x-1)).toList
    }

    def double(list:List[Int]):List[Int] ={
      list.map(x=>x*2)
    }

    def polygon(sides: Int, radius: Double, initialAngle: Angle): Image = {
      val increment = 360 / sides

      def loop(sides: Int, radius: Double, initialAngle:Angle): List[PathElement] = {
        (0 until sides).map(x=>lineTo(polar(radius, initialAngle+(increment*x).degrees))).toList
        }
      closedPath(moveTo(polar(radius,initialAngle)) :: loop(sides,radius,initialAngle))
    }

    def ascendingOpenInterval(startingVal:Int):List[Int] ={
      (startingVal to 1 by -1).map(x=>startingVal-(x-1)).toList
    }

  }

  def star(p:Int, n:Int,radius: Double): Image = {
    val increment = (Angle.one/ p)*n


    def loop(p: Int, n: Int, radius: Double): List[PathElement] = {
      (0 until p).map(x => lineTo(polar(radius,(increment * x)))).toList
    }

    closedPath(moveTo(polar(radius, 0.degrees)) :: loop(p, n, radius))
  }

  def allBeside(images: List[Image]): Image ={
    images match {
      case Nil =>Image.empty
      case hd :: tl => hd.beside(allBeside(tl))
    }
  }

  def allAbove(images: List[Image]): Image ={
    images match {
      case Nil =>Image.empty
      case hd :: tl => hd.above(allAbove(tl))
    }
  }

  val allAboveTest = allAbove(
    (1 to 5).toList map {skip => star(11,skip,100)
    }
  )

  val largerImage = allAbove(
    (3 to 11 by 2).toList map {points =>

      allBeside(
        (1 to points-2 by 2).toList map { skip =>
        star(points,skip,100)
        }
      )
  }
  )




}
