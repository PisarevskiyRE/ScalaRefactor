package Chapter_2

import java.time.LocalDate
import java.time.temporal.ChronoUnit

object Task1_right extends App{

  case class Student private(name: String, birth: String, enrollmentYear: Int)
  object Student{
    def apply(name: String, birth: String, enrollmentYear: Int) = new Student(name, birth, enrollmentYear)
  }


  class StudentInfoProvider(implicit now: LocalDate) {

    private def collect(student: Student): Map[String, String] = {

      val numYearsOfStudy = now.getYear - student.enrollmentYear


      val studentBirthday = convertStudentBirth(student.birth,"/")
      val studentAge = getAge(studentBirthday)

      Map("Age" -> studentAge.toString, "Course" -> numYearsOfStudy.toString)
    }

    private def getAge(from: LocalDate) = {
      ChronoUnit.YEARS.between(
        from,
        now)
    }


    private def convertStudentBirth(birthStr: String, separator: String): LocalDate = {
      val givenBirth: Array[String] = birthStr.split(separator)

      LocalDate.of(
        givenBirth(0).toInt,
        givenBirth(1).toInt,
        givenBirth(2).toInt
      )
    }

    def printInfo(student: Student) = {
      val studentInfo = collect(student)
      s"Name: [${student.name}]\n Age: [${studentInfo("Age")}] \nCourse: [${studentInfo("Course")}]"
    }
  }


  val bob = Student("Bob", "2003/01/20", 2020)
  implicit val localDateNow: LocalDate = LocalDate.now()

  new StudentInfoProvider().printInfo(bob)
}
