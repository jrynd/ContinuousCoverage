package com.jrynd.jobapplication.cigna.continuouscoverage

case class Cov(eff: Int, term: Int) {
  def overlaps(other : Cov) : Boolean = {
    (eff >= other.eff && eff <= other.term+1) ||
      (term+1 >= other.eff && term <= other.term)
  }
  def combine(overlapping : Cov) : Cov = {
    Cov(scala.math.min(eff, overlapping.eff),scala.math.max(term, overlapping.term)) 
  }
}
object Cov {
  def getLongestCoverage(covs: List[Cov]) : Cov = {
    //@tailrec
    def iter(
      bestSoFar : Cov,
      current : Cov,
      remaining : List[Cov],
    ) : Cov = {
      remaining match {
        case Nil => bestSoFar
        case head :: tail => {
          val newContinuous = if (current.overlaps(head)) {
            current.combine(head)
          }
          else {
            head
          }
          iter(
            if (newContinuous.term - newContinuous.eff > bestSoFar.term-bestSoFar.eff) {
              newContinuous
            }
            else {
              bestSoFar
            },
            newContinuous,
            tail,
          )
        }
      }
    }
    val sc = covs.sortWith(_.eff < _.eff)
    iter(sc.head, sc.head, sc.tail)
  }
}
