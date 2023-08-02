package finalTask.schemas

import java.time.LocalDate

object FileRule {
  val f: Array[String] => ExpensesFromFile = {
    line => {
      val id = line(0).toInt
      val date = LocalDate.parse(line(1))
      val expense_name = line(2)
      val expense_category = line(3)
      val amount = line(4).toDouble
      val currency = line(5)
      ExpensesFromFile(id, date, expense_name, expense_category, amount, currency)
    }
  }
}
