/**
  * Created by Christopher on 4/2/2017.
  */

import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._


object Chapter4 {

  object layer1 {
    val hi = "Hi!!"

      object layer2 {
      val hi = "Hi Hi!!" //playing around with scope, realize this is a bad idea

    }

  }

}


object Chapter4_2 {

  object Zero {
    val a = 1
    val b = 1
    val answer1 = a + b
  }
  println(Zero.answer1)

  /*****************************/
  object One {
    val a = 1

    object Two {
      val a = 3
      val b = 2
    }

    object Answer2 {
      val answer2 = a + Two.b
    }
  }
  println(One.Answer2.answer2)
  /******************************/

  object One_1 {
    val a = 5
    val b = 2

    object  Answer {

      val a = 1
      val answer = a + b
    }

  }

  println(One_1.Answer.answer)
  /***************************/


  object One_2 {
    val a = 1
    val b = a + 1
    val answer = a + b
  }

  println(One_2.answer)
  /***************************/
  object One_3 {
/*    val a = 1

    object Two {
      val b = 2
     }
    val answer = a + b
 */
    val answer = "context violation, within object One_3, there is no b except with in the context of Two"
  }

  println(One_3.answer)

  /***************************/

  object One_4 {
/*
    val a = b - 1
    val b = a + 1
    val answer = a + b
*/
  val answer = "this last one is recurive and won't work"
  }
  println(One_4.answer)
}

object archeryTarget{
  val eightRing = circle(40) fillColor (Color.red)
  val nineRing = circle(25) fillColor (Color.white)
  val tenRing = circle(10) fillColor (Color.red)
  val target = (tenRing on nineRing on eightRing)

  val ground = rectangle(100, 30) fillColor (Color.green)
  val stand = rectangle(25, 10) fillColor (Color.brown)
  val pole = rectangle(10, 25) fillColor (Color.white)

  val targetStand = (ground below stand below pole below target)
}

object streetScene {

  object neighborhood {
    object lot {
      val emptySpace = rectangle(80,80).lineWidth(0)

      object house {
        val houseHeight = 100
        val houseWidth = 100
        val roofHeight = 70
        val doorHeight = 50
        val doorWidth = 20

        val roof = triangle(houseWidth, roofHeight).fillColor(Color.darkRed).lineWidth(0)
        val walls = rectangle(houseWidth,houseHeight).fillColor(Color.red).lineWidth(0)
        val awning = rectangle (doorWidth,houseHeight-doorHeight).fillColor(Color.red).lineWidth(0)
        val door = rectangle(doorWidth,doorHeight).fillColor(Color.black).lineWidth(0)
        val entryWay = awning above door
        val house = roof above (entryWay on walls)
    }

      object tree {
        val leafs = circle(50).fillColor(Color.green).lineWidth(0)
        val trunk = rectangle(20,70).fillColor(Color.brown).lineWidth(0)
        val tree = leafs above trunk
    }

      val lot = house.house beside tree.tree beside emptySpace

    }

    val neighborhood = lot.lot beside lot.lot beside lot.lot
  }

  object street {
    val streetLength = 1000
    val streetHeight = 40
    val asphalt = rectangle(streetLength,streetHeight).fillColor(Color.black).noLine

    object markings {
      val numMarkings = 9
      val percentYellow = 0.7
      val yellows = rectangle(streetLength/numMarkings*percentYellow,streetHeight/3).fillColor(Color.yellow).noLine
      val betweenYellows = rectangle(streetLength/numMarkings*(1-percentYellow),streetHeight/3).fillColor(Color.black).noLine
      val markings = yellows beside betweenYellows beside yellows beside betweenYellows beside yellows beside betweenYellows beside yellows beside betweenYellows beside yellows beside betweenYellows beside yellows beside betweenYellows beside yellows beside betweenYellows beside yellows beside betweenYellows
    }

    val street = asphalt under markings.markings

  }

  val streetScene = neighborhood.neighborhood above street.street

}