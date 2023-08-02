package finalTask.transforms

import finalTask.schemas.{DataStract, Expenses}

trait Result

case class SingleResult(value: String) extends Result
case class MultiResult(value: Seq[Expenses]) extends Result

trait Metric {
  def Calculate[A <: DataStract, B <: Result](dataStruct: Seq[A], f: Seq[A] => B): B
}

object MetricFactory extends Metric {
  override def Calculate[A <: DataStract, B <: Result](dataStruct: Seq[A], f: Seq[A] => B): B = {
    f(dataStruct)
  }
}