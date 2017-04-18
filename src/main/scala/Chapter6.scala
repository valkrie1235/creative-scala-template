import doodle.core.Color
import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._
/**
  * Created by am_dev on 4/7/17.
  */
object Chapter6 {

    def boxes(color: Color): Image = {
      val box = Image.rectangle(40, 40).
        lineWidth(5.0).lineColor(color.spin(30.degrees)).
        fillColor(color)

      box beside box beside box beside box beside box
    }



    //Excercise Square
    def square(value: Int): Int = {
      value * value
    }

    //Excercise Halve
    def halve(value: Double): Double = {
      value/2
    }


    //See when method arguments are evaluated
    def active(x: Double, y: Double, z: String): Double = {
      val x = 5
      println(x)
      println(x*y)
      x * y

    }

}
