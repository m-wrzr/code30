import java.io.{File, PrintWriter}

import scala.io.Source

/**
  * Created by martinwurzer on 20.04.16.
  */

object solution {

  // minesweeper solver

  // setup
  val inputSize = "large"
  var output = ""
  val filename = "A-" + inputSize + "-practice.in"

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()
    val ncase = lines.next().toInt

    // N, 2 × N, 3 × N - keep track of digits
    // when all digits are there, stop
    // out: last number

    for (mycase <- 1 to ncase) {

      val result = rec(lines.next().toInt, 1, Set(), Set())

      if (result == -1) {

        output += s"Case #$mycase: INSOMNIA\n"

      } else {

        output += s"Case #$mycase: $result\n"
      }
    }

    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }

  def getDigits(i: Int): Set[Int] = {

    i.toString.split("").map(_.toInt).toSet
  }

  def rec(n: Int, i: Int, digits: Set[Int], used: Set[Int]): Int = {

    if (used.contains(n * i)) {
      return -1
    }
    if (digits.size == 10) {
      return n * (i - 1)
    }

    rec(n, i + 1, digits.union(getDigits(n * i)), used.+(n * i))
  }
}