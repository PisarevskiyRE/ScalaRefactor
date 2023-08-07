package finalTask.schemas

import java.time.LocalDate

trait DataFile{
  val id: Int
  val date: LocalDate
  val expense_name: String
  val expense_category: String
  val amount: Double
  val currency: String
}

case class ExpensesFromFile(
                     id: Int,
                     date: LocalDate,
                     expense_name: String,
                     expense_category: String,
                     amount: Double,
                     currency: String
                   ) extends DataFile

