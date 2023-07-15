object Main extends Enumeration
{
  type Main = Value

  // Assigning values
  val first = Value("Thriller")
  val second = Value("Horror")
  val third = Value("Comedy")
  val fourth = Value("Romance")

  // Main method
  def main(args: Array[String])
  {
    println(s"The third value = ${Main.third}")
  }
}




import java.time.LocalDate
import java.time.Period

def calculateAge(birthDate: java.sql.Date): Int = {
  val birthLocalDate = birthDate.toLocalDate
  val currentDate = LocalDate.now()
  val period = Period.between(birthLocalDate, currentDate)
  period.getYears
}

