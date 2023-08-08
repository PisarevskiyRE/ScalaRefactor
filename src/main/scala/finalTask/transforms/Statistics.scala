package finalTask.transforms

import finalTask.schemas._

trait Statistics {
  def Calculate[A <: DataStruct, B <: Result](dataStruct: Seq[A], mainFunction: Seq[A] => B, filters: Filters[A]): B
}

object StatisticsCalculator extends Statistics {
  override def Calculate[A <: DataStruct, B <: Result](dataStruct: Seq[A], mainFunction: Seq[A] => B,  filters: Filters[A]): B = {

    val filteredData = filters.filters.foldLeft(dataStruct) { (data, filter) =>
      filter(data)
    }

    mainFunction(
      filteredData
    )

  }
}
