package finalTask

import finalTask.schemas._

object Main2 extends App {
  val dollar = Currency(1, "USD") * 100
  val euro = Currency(1, "EUR") * 100

  println(dollar)
  println(euro)

  val convertedEuro = dollar.convert("EUR")
  println(convertedEuro)

  val convertedDollar = euro.convert("USD")
  println(convertedDollar)
}