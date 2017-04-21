/**
  * Created by am_dev on 4/21/17.
  */

import scala.annotation.tailrec

object Class3 {

  def loop(n: Int): Int =
    n match {
      case 0 => 0
      case n => 1+ loop(n-1)
    }

  def sum2(count:Int, accumulator:Int):Int = {
    count match {
      case 0=>accumulator
      case n=>sum2 (n-1,accumulator+1)
    }
  }


  def odd(n: Int): Boolean =
    n match {
      case 1 => false
      case n => even(n-1)
    }


  def even(n: Int): Boolean = {
    n match {
      case 1 =>false
      case n=> odd(n-1)
    }
  }


}
