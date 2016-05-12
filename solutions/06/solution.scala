import java.io.{File, PrintWriter}

import scala.io.Source

/**
  * Created by wrzr on 18.04.16.
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
      val tmp = lines.next().split(" ").map(_.toInt)
      val n = tmp(0)
      val k = tmp(1)

      val grid = Array.ofDim[Char](n, n)

      // init grid
      for {i <- 0 until n} yield
        grid(i) = lines.next().toCharArray

      val rotated = Array.ofDim[Char](n, n)

      // rotate
      for {i <- 0 until n
           j <- 0 until n} yield
        rotated(j)(n - 1 - i) = grid(i)(j)

      val gravity = Array.ofDim[Char](n, n)

      // gravity
      for (col <- 0 until n) {

        var invassigned = n - 1

        for (toassign <- rotated map (_ (col)) reverse) {

          if (toassign != '.') {

            gravity(invassigned)(col) = toassign
            invassigned -= 1
          }
        }
      }

      // DEBUGGING
      println(s"CASE $mycase")
      println("\nORIG")
      println("\nGRAVITY")
      printGrid(gravity)

      val result = getWinString(k, n, gravity)
      output += s"Case #$mycase: $result\n"
    }

    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }


  def isWinHorizontal(player: Char, k: Int, n: Int, grid: Array[Array[Char]]): Boolean = {

    for (row <- grid) {
      row sliding k foreach (l => if (l.length >= k && l.toSet.size == 1 && l.toSet(player)) {
        println("WIN VERTICAL FOR " + player)
        return true
      })
    }

    false
  }

  def isWinVertical(player: Char, k: Int, n: Int, grid: Array[Array[Char]]): Boolean = {

    for (col <- 0 until n) {
      grid map (_ (col)) sliding k foreach (l => if (l.length >= k && l.toSet.size == 1 && l.toSet(player)) {
        println("WIN VERTICAL FOR " + player)
        return true
      })
    }

    false
  }

  // start from the cubes sides and get all diagonals
  def isWinDiagonal(player: Char, k: Int, n: Int, grid: Array[Array[Char]]): Boolean = {

    for (i <- 0 until n) {

      getDiagonalLR(k, n, grid, 0, i) sliding k foreach (l => if (l.length >= k && l.toSet.size == 1 && l.toSet(player)) {
        return true
      })
      getDiagonalLR(k, n, grid, i, 0) sliding k foreach (l => if (l.length >= k && l.toSet.size == 1 && l.toSet(player)) {
        return true
      })
      getDiagonalRL(k, n, grid, 0, i) sliding k foreach (l => if (l.length >= k && l.toSet.size == 1 && l.toSet(player)) {
        return true
      })
      getDiagonalRL(k, n, grid, i, n - 1) sliding k foreach (l => if (l.length >= k && l.toSet.size == 1 && l.toSet(player)) {
        return true
      })
    }

    false
  }


  // LR == left to right
  def getDiagonalLR(k: Int, n: Int, grid: Array[Array[Char]], i: Int, j: Int): List[Char] = {

    var list: List[Char] = List()

    for (iter <- 0 until n) {

      if (i + iter == n || j + iter == n) {
        return list
      }
      list = grid(i + iter)(j + iter) :: list
    }

    list
  }

  // RL == right to left
  def getDiagonalRL(k: Int, n: Int, grid: Array[Array[Char]], i: Int, j: Int): List[Char] = {


    var list: List[Char] = List()

    for (iter <- 0 until n) {

      if (i + iter == n || j - iter == -1) {
        return list
      }
      list = grid(i + iter)(j - iter) :: list
    }

    list
  }


  def getWinString(k: Int, n: Int, grid: Array[Array[Char]]): String = {

    val redWin = isWinHorizontal('R', k, n, grid) || isWinVertical('R', k, n, grid) || isWinDiagonal('R', k, n, grid)
    val blueWin = isWinHorizontal('B', k, n, grid) || isWinVertical('B', k, n, grid) || isWinDiagonal('B', k, n, grid)

    if (redWin && blueWin) {
      return "Both"
    }
    if (redWin) {
      return "Red"
    }
    if (blueWin) {
      return "Blue"
    }

    "Neither"
  }


  def printGrid(grid: Array[Array[Char]]): Unit = {

    for (str <- grid map (_.deep.mkString(""))) {
      println(str)
    }
  }
}