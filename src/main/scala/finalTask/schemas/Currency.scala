package finalTask.schemas


import finalTask.transforms.Converter

case class Currency(amount: Double, designation: String) {
  def + (that: Currency): Currency =
    Currency(this.amount + that.amount, designation)

  def * (x: Double): Currency =
    Currency((this.amount * x).toDouble, designation)

  def - (that: Currency): Currency =
    Currency(this.amount - that.amount, designation)

  def / (that: Double): Currency =
    Currency((this.amount / that).toDouble, designation)

  def / (that: Currency): Double =
    this.amount.toDouble / that.amount

  def convert(to: String): Currency = {
    val rate = Converter.exchangeRate(designation)(to)
    val convertedAmount = (this.amount.toDouble * rate).toDouble
    Currency(convertedAmount, to)
  }

  private def decimals(n: Double): Int =
    if (n == 1) 0 else 1 + decimals(n / 10)

  override def toString: String =
    ((amount.toDouble / Currency(1, designation).amount.toDouble)
      formatted ("%." + decimals(Currency(1, designation).amount) + "f")
      + " " + designation)
}

