/**
  * Created by martinwurzer on 30.04.16.
  */

import java.io.{File, PrintWriter}

import scala.io.Source

object Solution {

  // setup
  val inputSize = "large"
  var output = ""
  val filename = "A-" + inputSize + "-practice.in"

  /*
   N bushes planted side by side in a straight line
   bush #2 and move to the right
   cuts the top of each bush to make it exactly as tall as the average of the two bushes on either side
   already as short as the average or shorter, then the gardener does not touch

   out:  height of bush number N - 1
   */

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()
    val ncase = lines.next().toInt

    for (mycase <- 1 to ncase) {

      val n = lines.next().toInt
      val heights = lines.next().split(" ").map(_.toInt).map(_ * 1.0)

      for (i <- 1 until (n - 1)) {

        val newHeight = (heights(i - 1) + heights(i + 1)) / 2

        if (newHeight < heights(i)) {
          heights(i) = newHeight
        }
      }

      output += s"Case #$mycase: ${heights(n - 2)}\n"
    }

    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }
}