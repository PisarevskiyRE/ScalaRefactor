import java.time.LocalDate
import java.time.Period

def calculateAge(birthDate: java.sql.Date): Int = {
  val birthLocalDate = birthDate.toLocalDate
  val currentDate = LocalDate.now()
  val period = Period.between(birthLocalDate, currentDate)
  period.getYears
}