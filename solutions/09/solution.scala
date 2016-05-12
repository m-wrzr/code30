import java.io.{File, PrintWriter}

import scala.io.Source


/**
  * Created by martinwurzer on 21.04.16.
  *
  * solution is probably too slow, but i can't really check it with codejam & don't have time right now ;)
  */
object solution {


  // setup
  val inputSize = "large"
  var output = ""
  val filename = "A-" + inputSize + "-practice.in"

  def main(args: Array[String]) {

    val lines = Source.fromFile(filename).getLines()
    val ncase = lines.next().toInt

    /*
    You receive a credit C at a local store and would like to buy two items.
    You first walk through the store and
    create a list L of all available items. From this list you would like to buy two items that
    add up to the entire value of the credit.
    The solution you provide will consist of the two integers indicating
    the positions of the items in your list (smaller number first).
     */

    for (mycase <- 1 to ncase) {

      val c = lines.next().toInt
      val l = lines.next().toInt

      val prices = lines.next().split(" ").map(_.toInt)

      output += s"Case #$mycase: ${getRealIndices(prices, c)}\n"
    }

    print(output)

    val writer = new PrintWriter(new File("A-" + inputSize + "-output.txt"))
    writer.write(output)
    writer.close()
  }

  def getRealIndices(prices: Array[Int], c: Int): String = {

    val price = getPrices(prices, c)
    
    // case same value indexes returned
    if (price._1 == price._2) {

      val index1 = prices.indexOf(price._1)
      val index2 = prices.indexOf(price._2, index1)

      return (index1 + 1) + " " + (index2 + 2)
    }

    (prices.indexOf(price._1) + 1) + " " + (prices.indexOf(price._2) + 1)
  }

  def getPrices(prices: Array[Int], c: Int): (Int, Int) = {

    prices.combinations(2).foreach(l => if (l(0) + l(1) == c) {
      return (l(0), l(1))
    })

    (-1, -1)
  }
}