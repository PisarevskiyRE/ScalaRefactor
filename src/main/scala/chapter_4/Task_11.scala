package chapter_4

object Task_11 extends App{




  final case class Year private(value: Int) {
    def increase(): Year = Year(this.value + 1)
  }

  object Year{
    private def isPositiveNumber(value: Int): Boolean = {
      if (value > 0)
        true
      else
        false
    }

    def apply(value: Int): Year = {
      if (isPositiveNumber(value))
        new Year(value)
      else
        throw new IllegalArgumentException("Год должен быть положительным")
    }
  }


  val t = Year(1989).increase()




}
