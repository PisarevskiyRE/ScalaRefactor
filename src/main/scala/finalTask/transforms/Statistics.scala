package finalTask.transforms

import finalTask.schemas.{DataStruct, Expenses}

trait Statistics {
  def Calculate[A <: DataStruct, B <: Result](dataStruct: Seq[A], f: Seq[A] => B): B
}

object StatisticsCalculator extends Statistics {
  override def Calculate[A <: DataStruct, B <: Result](dataStruct: Seq[A], f: Seq[A] => B): B = {
    f(dataStruct)
  }
}
