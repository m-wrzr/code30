/**
  * Created by martinwurzer on 30.04.16.
  */

import java.io.{File, PrintWriter}

import scala.io.Source

object Solution {

  // setup
  val inputSize = "small"
  var output = ""
  val filename = "A-" + inputSize + "-practice.in"

  /*
   absorb only if smaller
   get the minimum number of absorbs/additions of moths
   */

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()
    val ncase = lines.next().toInt

    for (mycase <- 1 to ncase) {

      var Array(a, n) = lines.next().split(" ").map(_.toInt)
      val sizes = lines.next().split(" ").map(_.toInt).sorted

      if (a != 1) {

        var additions = 0
        var overall = n

        for (i <- 0 until n) yield {

          while (sizes(i) >= a) {
            a += a - 1
            additions += 1
          }
          a += sizes(i)
          // either delete rest or add x
          overall = math.min(additions + n - i - 1, overall)
        }

        output += s"Case #$mycase: $overall\n"
      } else {
        output += s"Case #$mycase: $n\n"
      }
    }
    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }
}