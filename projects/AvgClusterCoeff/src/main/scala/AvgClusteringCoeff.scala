import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

object AvgClusteringCoeff {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Average Clustering Coefficient")
    val sc = new SparkContext(conf)

    val users = sc.textFile("../../data/users.txt").map { line =>
      val fields = line.split(",")
      (fields(0).toLong, fields(1))
    }

    val graph = GraphLoader.edgeListFile(sc, "../../data/connections.txt")

    val coeffs = graph.collectNeighbors(EdgeDirection.Either).collect.map { case (id, neighboors) => (1, {

      var count = 0
      var total = 0
      for (nb <- neighboors) {
        for (nb2 <- neighboors) {
          if (nb._1 != nb2._1) {
            total += 1
            count += graph.triplets.filter(t => t.srcId == nb._1).filter(t => t.dstId == nb2._1).count.toInt
          }
        }
      }
      total = total/2
      var coeff = count.toFloat/total
      println(s"Clustering Coefficient for vertex $id is: $coeff")

      coeff
    })
    }.reduce((a, b) => (a._1 + b._1, a._2 + b._2))

    val AvgClusteringCoeff = coeffs._2 / coeffs._1

    println(s"Average Clustering Coefficient for this graph is: $AvgClusteringCoeff")

  }
}

