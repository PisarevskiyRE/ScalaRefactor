package finalTask.schemas

import java.time.LocalDate

trait Rule{
  val f: Array[String] => ExpensesFromFile
}


// значенияд создавал, думал сначала проверки добавить и забыл
object FileRule extends Rule{
  val f: Array[String] => ExpensesFromFile = {
    line => {
      ExpensesFromFile(
        line(0).toInt,
        LocalDate.parse(line(1)),
        line(2),
        line(3),
        line(4).toDouble,
        line(5))
    }
  }
}
