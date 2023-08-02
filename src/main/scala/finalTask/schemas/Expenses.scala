package finalTask.schemas

import java.time.LocalDate

trait DataStruct{
  val id: Int
  val date: LocalDate
  val expense_name: String
  val expense_category: String
  val currency: Currency
}

case class Expenses(
                             id: Int,
                             date: LocalDate,
                             expense_name: String,
                             expense_category: String,
                             currency: Currency
                           ) extends DataStruct

