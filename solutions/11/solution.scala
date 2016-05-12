/**
  * Created by martinwurzer on 23.04.16.
  */

import java.io.{File, PrintWriter}

import scala.io.Source

object solution {


  // setup
  val inputSize = "large"
  var output = ""
  val filename = "B-" + inputSize + "-practice.in"

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()
    val ncase = lines.next().toInt

    // ASC in row/col
    // unique solution for missing list exists

    // all odd numbers are on missing list -> sort those numbers

    for (mycase <- 1 to ncase) {

      val n = lines.next().toInt
      var amountUsed: Map[String, Int] = Map()

      for (i <- 0 until 2 * n - 1) {

        lines.next().split(" ").foreach(x => if (amountUsed.contains(x)) {

          amountUsed += (x -> (amountUsed(x) + 1))
        }
        else {
          amountUsed += (x -> 1)
        })
      }

      var result: List[String] = List()

      amountUsed.keySet.foreach(key => if (amountUsed(key) % 2 != 0) {

        result = key :: result
      })

      output += s"Case #$mycase: ${result.sortBy(_.toInt).mkString(" ")}\n"
    }

    print(output)

    val writer = new PrintWriter(new File("B-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }
}