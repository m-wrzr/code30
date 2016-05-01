/**
  * Created by martinwurzer on 01.05.16.
  */

import java.io.{File, PrintWriter}

import scala.io.Source

object Solution {

  /* basic idea is to build an array of the used chars as well as their amount
  and adjust each frequency by the difference to the median */
  def solveForCase(mycase: Int, lines: Iterator[String]): String = {

    val n = lines.next().toInt

    val words: Array[Array[Char]] = Array.ofDim(n)
    val amounts: Array[List[Int]] = Array.ofDim(n)
    val sequences: Array[List[Char]] = Array.ofDim(n)

    for (i <- 0 until n) {

      words(i) = lines.next().toCharArray
      amounts(i) = List(1)
      sequences(i) = List(words(i)(0))

      if (words(i).length > 1) {

        words(i).sliding(2).foreach(l => if (l(0) == l(1)) {

          amounts(i) = amounts(i).updated(0, amounts(i).head + 1)
        } else {

          // add new word to sequence
          sequences(i) = l(1) :: sequences(i)
          // add 1 as new amount to amounts
          amounts(i) = 1 :: amounts(i)
        })
      }
    }

    // sequences of chars not the same
    sequences.sliding(2).foreach(l => if (l(0) != l(1)) {
      return s"Case #$mycase: Fegla Won\n"
    })

    var nMoves = 0

    for (i <- amounts(0).indices) {

      val sorted = amounts.map(l => l(i)).sorted
      val median = Math.floor(sorted.length / 2).asInstanceOf[Int]

      sorted.foreach(x => nMoves += Math.abs(x - sorted(median)))
    }

    s"Case #$mycase: $nMoves\n"
  }

  def main(args: Array[String]) {

    val inputSize = "large"
    val filename = "A-" + inputSize + "-practice.in"
    val lines = Source.fromFile(filename).getLines()
    val ncase = lines.next().toInt
    var output = ""

    for (mycase <- 1 to ncase) {
      output += solveForCase(mycase, lines)
      println("FINISHED " + mycase)
    }

    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }
}