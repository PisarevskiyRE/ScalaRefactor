package finalTask

object Converter {
  val exchangeRate = Map(
    "USD" -> Map("USD" -> 1.0, "EUR" -> 0.7596, "RUB" -> 1.211),
    "EUR" -> Map("USD" -> 1.316, "EUR" -> 1.0, "RUB" -> 1.594),
    "RUB" -> Map("USD" -> 0.8257, "EUR" -> 0.6272, "RUB" -> 1.0)
  )
}

case class Currency(amount: Long, designation: String) {
  def + (that: Currency): Currency =
    Currency(this.amount + that.amount, designation)

  def * (x: Double): Currency =
    Currency((this.amount * x).toLong, designation)

  def - (that: Currency): Currency =
    Currency(this.amount - that.amount, designation)

  def / (that: Double): Currency =
    Currency((this.amount / that).toLong, designation)

  def / (that: Currency): Double =
    this.amount.toDouble / that.amount

  def convert(to: String): Currency = {
    val rate = Converter.exchangeRate(designation)(to)
    val convertedAmount = (this.amount.toDouble * rate).toLong
    Currency(convertedAmount, to)
  }

  private def decimals(n: Long): Int =
    if (n == 1) 0 else 1 + decimals(n / 10)

  override def toString: String =
    ((amount.toDouble / Currency(1, designation).amount.toDouble)
      formatted ("%." + decimals(Currency(1, designation).amount) + "f")
      + " " + designation)
}

object US {
  val Dollar: Currency = Currency(1, "USD")
}

object Europe {
  val Euro: Currency = Currency(1, "EUR")
}

object Main2 extends App {
  val dollar = US.Dollar * 100
  val euro = Europe.Euro * 1000

  println(dollar)
  println(euro)

  val convertedEuro = dollar.convert("EUR")
  println(convertedEuro)

  val convertedDollar = euro.convert("USD")
  println(convertedDollar)
}