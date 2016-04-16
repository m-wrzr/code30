import java.io._

import scala.io.Source

/**
  * Created by wrzr on 16.04.16.
  */
object Solution {

  // plate at start and in 10 sec intervals

  // 1) eating at any time
  // 2) constant rate whenever mushrooms are there

  // setup
  val inputSize = "large"
  var output = ""
  val filename = "A-" + inputSize + "-practice.in"

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()
    val ncase = lines.next().toInt

    for (mycase <- 1 to ncase) {

      val n = lines.next().toInt
      val intervals = lines.next().split(" ").map(_.toInt)

      val fst = solveFst(n, intervals)
      val snd = solveSnd(n, intervals)

      output += s"Case #$mycase: $fst $snd\n"
    }

    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }

  // sum up difference between intervals
  def solveFst(n: Int, intervals: Array[Int]): Int = {

    intervals.sliding(2).map(l => getDiff(l(0) - l(1))) sum
  }

  // find the biggest difference between two intervals - this is the constant speed
  def solveSnd(n: Int, intervals: Array[Int]): Int = {

    val maxdiff = intervals.sliding(2).map(l => getDiff(l(0) - l(1))) max

    // get the constant snack time or the number of shrooms
    intervals.dropRight(1) map (x => Math.min(x, maxdiff)) sum
  }

  // is pattern matching with conditional stmts still pattern matching? ^^
  def getDiff(diff: Int): Int = diff match {
    case _ if diff > 0 => diff
    case _ => 0
  }
}