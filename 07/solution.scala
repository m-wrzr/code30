import java.io.{File, PrintWriter}

import scala.io.Source

/**
  * Created by wrzr on 19.04.16.
  */
object Solution {


  // setup
  val inputSize = "large"
  var output = ""
  val filename = "A-" + inputSize + "-practice.in"

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()
    val ncase = lines.next().toInt

    for (mycase <- 1 to ncase) {

      val n = lines.next().toInt

      var ropes: List[Array[Int]] = List()

      for (r <- 1 to n) {

        ropes = lines.next().split(" ").map(_.toInt) :: ropes
      }

      var nintersect = 0

      ropes combinations 2 foreach (l => if (isIntersection(l.head, l.last)) {
        nintersect += 1
      })

      output += s"Case #$mycase: $nintersect\n"
    }

    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }

  def isIntersection(fst: Array[Int], snd: Array[Int]): Boolean = {

    if (fst(0) < snd(0) && fst(1) > snd(1) || fst(0) > snd(0) && fst(1) < snd(1)) {
      return true
    }

    false
  }

}