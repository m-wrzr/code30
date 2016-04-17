import java.io.{File, PrintWriter}

import scala.io.Source

/**
  * Created by wrzr on 17.04.16.
  */
object Solution {

  // minesweeper solver

  // setup
  val inputSize = "large"
  var output = ""
  val filename = "A-" + inputSize + "-practice.in"

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()
    val ncase = lines.next().toInt

    for (mycase <- 1 to ncase) {

      // n x n grid
      val n = lines.next().toInt
      val grid = Array.ofDim[Char](n, n)

      // init grid
      for {i <- 0 until n} yield
        grid(i) = lines.next().toCharArray


      // visit all mines
      for {
        i <- 0 until n
        j <- 0 until n
      }
        yield if (grid(i)(j) == '*') {
          handleMine(i, j, n, grid)
        }

      // fill unvisited
      for {
        i <- 0 until n
        j <- 0 until n
      }
        yield if (grid(i)(j) == '.') {
          grid(i)(j) = '0'
        }

      var nclicks = 0

      // open 0 recursive
      for {
        i <- 0 until n
        j <- 0 until n
      }
        yield if (grid(i)(j) == '0') {
          nclicks += 1
          openZero(i, j, n, grid)
        }

      // count all remaining numbers afterwards
      for {
        i <- 0 until n
        j <- 0 until n
      }
        yield if (grid(i)(j) != '*' && grid(i)(j) != 'v') {
          nclicks += 1
        }

      output += s"Case #$mycase: $nclicks\n"
    }

    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }

  def openZero(i: Int, j: Int, n: Int, grid: Array[Array[Char]]): Unit = {

    for {
      k1 <- Math.max(0, i - 1) to Math.min(n - 1, i + 1)
      k2 <- Math.max(0, j - 1) to Math.min(n - 1, j + 1)
    }
      yield if (grid(k1)(k2) == '0') {
        grid(k1)(k2) = 'v'
        openZero(k1, k2, n, grid)
      } else {
        grid(k1)(k2) = 'v'
      }
  }

  def handleMine(i: Int, j: Int, n: Int, grid: Array[Array[Char]]): Unit = {

    for {
      k1 <- Math.max(0, i - 1) to Math.min(n - 1, i + 1)
      k2 <- Math.max(0, j - 1) to Math.min(n - 1, j + 1)
    }
      yield grid(k1)(k2) = increment(grid(k1)(k2))
  }

  def increment(c: Char): Char = {

    if (c != '*') {

      if (c == '.') {
        return '1'
      }

      return (c.toInt + 1).toChar
    }

    c
  }
}