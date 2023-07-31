package finalTask.schemas

import java.time.LocalDate

// для сырых данных из файла
case class ExpensesFromFile(
                     id: Int,
                     date: LocalDate,
                     expense_name: String,
                     expense_category: String,
                     amount: Double,
                     currency: String
                   ) extends DataFile

