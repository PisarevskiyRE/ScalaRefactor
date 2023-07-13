package chapter_4

object Task_14 extends App{

  def sum(a: Int, b: Int): Int = {

    require(
      a >= 0 || b >= 0,
      "Параметры должны быть больше или равен 0."
    )

    val result = a + b
    result

  } ensuring(_ > 0, "Integer Overflow")



  println(sum(2000000000,2000000000))

}
