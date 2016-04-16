import java.io._

import scala.io.Source

/**
  * Created by wrzr on 16.04.16.
  */
object Solution {

  // plate at start and in 10 sec intervals

  // 1) eating at any time
  // 2) constant rate whenever mushrooms are there

  val inputSize = "small"
  var output: String = ""
  val filename = "A-" + inputSize + "-practice.in"

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()

    val ncase = lines.next().toInt

    for (mycase <- 1 to ncase) {

      val n = lines.next().toInt
      val intervals: Array[Int] = lines.next().split(" ").map(_.toInt)

      val fst = solveFst(n, intervals)
      val snd = solveSnd(n, intervals)

      output += s"Case #$mycase: $fst $snd\n"
    }

    print(output)
    val file = new File("A-" + inputSize + "-output.txt")
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(output)
    bw.close()
  }

  // sum up difference between intervals
  def solveFst(n: Int, intervals: Array[Int]): Int = {

    var sum = 0

    for (i <- 0 to (n - 2)) {

      sum += getDiff(i, intervals)
    }

    sum
  }

  // find the biggest difference between two intervals - this is the constant speed
  def solveSnd(n: Int, intervals: Array[Int]): Int = {

    var diffList = List[Int]()

    for (i <- 0 to (n - 2)) {

      diffList = getDiff(i, intervals) :: diffList
    }

    val maxdiff = diffList.max
    var sum = 0

    for (i <- 0 to (n - 2)) {

      sum += Math.min(intervals(i), maxdiff)
    }

    sum
  }

  def getDiff(i: Int, intervals: Array[Int]): Int = {

    val diff = intervals(i) - intervals(i + 1)

    if (diff > 0) {
      return diff
    }

    0
  }
}