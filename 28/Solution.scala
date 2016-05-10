import java.io.{File, PrintWriter}

import scala.io.Source

object Solution {

  // setup
  val inputSize = "small"
  var output = ""
  val filename = "A-" + inputSize + "-practice.in"

  /*
  N parties
  remove 1 or 2 senators at a time
  evacuate s.t. no party has absolute majority
   */
  var members = scala.collection.mutable.Map[String, Int]()
  var total = 0

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()

    for (mycase <- 1 to lines.next().toInt) {

      val n = lines.next().toInt

      members = scala.collection.mutable.Map[String, Int]()
      total = 0

      lines.next().split(" ").map(_.toInt)
        .zipWithIndex
        .foreach { case (e, i) =>
          total += e
          members += (('A' + i).toChar.toString -> e)
        }

      if (n == 2) {
        output += s"Case #$mycase:${" AB" * members.getOrElse("A", 0)}\n"

      } else {

        // remove one at a time from largest party until two left
        output += s"Case #$mycase: ${(0 until total - 1).map(_ => evacuate()).mkString(" ")}\n"
      }
    }
    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }

  def evacuate(): String = {

    if (members.size == 2) {
      return members.keySet.mkString("")
    }
    val max = members.maxBy(_._2)

    if (max._2 == 1) {
      members.remove(max._1)
    } else {
      members.put(max._1, max._2 - 1)
    }
    total -= 1
    max._1
  }
}