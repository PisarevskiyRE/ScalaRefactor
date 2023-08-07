package finalTask.transforms

import finalTask.schemas.Expenses

trait Result

case class SingleResult(value: String) extends Result
case class MultiResult(value: Seq[Expenses]) extends Result
