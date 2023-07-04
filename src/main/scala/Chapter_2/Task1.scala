package Chapter_2

import java.time.LocalDate
import java.time.temporal.ChronoUnit

object Task1 extends App{

  //val currentYear = 2023
  //val currentMonth = 5
  //val currentDate = 13

  class Student(val name: String, val birth: String, val enrollmentYear: Int) {
    def printInfo(): Unit = {
      val studentInfo = new StudentInfo(this).collect()
      println(studentInfo)
    }
  }

  val bob = new Student("Bob", "2003/01/20", 2020)
  bob.printInfo()


  class StudentInfo(student: Student) {
    def collect(): String = {
      val course = LocalDate.now().getYear - student.enrollmentYear

      val givenBirth = student.birth.split("/")

      val birthday = LocalDate.of(
        givenBirth(0).toInt,
        givenBirth(1).toInt,
        givenBirth(2).toInt)

      val age = ChronoUnit.YEARS.between(
        birthday,
        LocalDate.now()
      )

      s"Name: [${student.name}]\n Age: [$age] \nCourse: [$course]"
    }
  }
}
