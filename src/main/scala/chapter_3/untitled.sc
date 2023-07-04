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