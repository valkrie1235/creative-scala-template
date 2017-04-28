/**
  * Created by am_dev on 4/28/17.
  */

import doodle.core.Color
import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._
import cats.Monoid
import cats.implicits._




object Class4 {

  implicit object pointInstance extends Monoid[Point] {
    def empty = Point.zero
    def combine (x: Point, y: Point): Point =
      Point(x.x + y.x, x.y + y.y)
  }

  val addN = (n:Int) =>(x:Int) =>x+n

  val circle = (frequency: Double) => (a: Angle) => Point.polar(1.0, a * frequency)
  val scale = (r: Double) => (pt: Point) => Point(pt.x * r, pt.y * r)

  val curve: Double => Angle => Point =
    (r: Double) =>
      (circle(1) andThen scale(r)) |+| (circle(6) andThen scale(r/2)) |+| (circle(-14) andThen scale(r/3))




  val sample: Int => (Angle => Image) => Image = (n: Int) => {
    val step = Angle.one/n

    (parametric: (Angle=>Image)) => {
    def loop(count: Int): Image =
    count match {
      case 0 => Image.empty
      case n => parametric(step * n) on loop(n-1)
    }
      loop(n)
  }

  }

  val style: Point => Image = {
    val c = curve(0.53)
    (pt: Point) => {
      val color = c(pt.angle)
      Image.circle(3).
        at(pt.toVec).
        lineColor(Color.royalBlue).
        fillColor(Color.hsla(color.angle, color.r.normalized, 0.7.normalized, 0.5.normalized))
    }
  }

val image = sample(1000)(curve(200) andThen style)

}
