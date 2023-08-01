package finalTask.transforms

import finalTask.schemas.DataStract

trait Result

case class SingleResult(value: String) extends Result
case class SeqResult(value: Seq[String]) extends Result

trait Metric {
  def Calculate[A <: DataStract, B <: Result](dataStract: Seq[A], f: Seq[A] => B): B
}

object MetricFactory extends Metric {
  override def Calculate[A <: DataStract, B <: Result](dataStract: Seq[A], f: Seq[A] => B): B = {
    f(dataStract)
  }
}