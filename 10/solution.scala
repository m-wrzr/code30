/**
  * Created by martinwurzer on 22.04.16.
  */

import java.io.{File, PrintWriter}

import scala.io.Source

object solution {


  // setup
  val inputSize = "small"
  var output = ""
  val filename = "A-" + inputSize + "-practice.in"

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()
    val ncase = lines.next().toInt

    for (mycase <- 1 to ncase) {
      
      var res: List[Char] = List(' ')

      // put lower behind, put higher in front
      lines.next().toCharArray.foreach(c => if (c.compare(res.head) < 0) {
        res = res :+ c
      } else {
        res = c :: res
      })

      // remove init space
      output += s"Case #$mycase: ${res.mkString("").replace(" ", "")}\n"
    }

    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }

}