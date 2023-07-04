package Chapter_2

object Task3 extends App{

  val employees = List("Bob", "Liesa","Bob", "Liesa","Bob", "Liesa")

  val baseSalary = 10
/*
* Ваша задача - исправить ситуацию, чтобы метод salarySupplement(), ориентированный на возвращение значения,
* только возвращал значение (без каких-либо побочных эффектов). При этом вы должны только реорганизовать код,
* поведение кода не должно измениться: если значение для salarySupplement 0.25,
* то уведомление ("some side effect: notification") должно отправляться.
* */

  def sendNotification(): Unit = println("some side effect: notification")

  def employeeNumber(): Int = employees.length

  def salarySupplementWithNotification(): (Double, Boolean) = {

    if (employeeNumber() > 5) (0.25, true)
    else (0.1, false)

  }

  def createReport(): Unit = {

    val (supplement, isSendNotification) = salarySupplementWithNotification()

    val salary: Double = baseSalary + baseSalary * supplement

    if (isSendNotification) sendNotification

  }



  createReport()
}
