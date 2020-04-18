package com.jrynd.jobapplication.cigna.continuouscoverage

object TestContinuousCoverage {
  val coverages = List(
    Cov(1, 20),
    Cov(21, 30),
    Cov(15, 25),
    Cov(28, 40),
    Cov(50, 60),
    Cov(61, 200),
  )
 
  def main(args: Array[String]): Unit = {
    val longestCoverage = Cov.getLongestCoverage(coverages)

    println(s"For coverages $coverages\nlongest is $longestCoverage")
  }
}
