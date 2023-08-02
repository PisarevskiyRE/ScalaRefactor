package finalTask.schemas

import finalTask.transforms.Converter

case class Currency(amount: Double, designation: String) {

  def +(that: Currency)(implicit defaultCurrency: String): Currency =
    if (designation == defaultCurrency)
      Currency(this.amount + that.amount, designation)
    else {
      val convertedAmount = this.convert(defaultCurrency).amount + that.amount
      Currency(convertedAmount, defaultCurrency)
    }

  def *(x: Double): Currency =
    Currency((this.amount * x), designation)

  def -(that: Currency)(implicit defaultCurrency: String): Currency =
    if (designation == defaultCurrency)
      Currency(this.amount - that.amount, designation)
    else {
      val convertedAmount = this.convert(defaultCurrency).amount - that.amount
      Currency(convertedAmount, defaultCurrency)
    }

  def convert(to: String): Currency = {
    val rate = Converter.exchangeRate(designation)(to)
    val convertedAmount = (this.amount * rate)
    Currency(convertedAmount, to)
  }

  private def decimals(n: Double): Int =
    if (n == 1) 0 else 1 + decimals(n / 10)

  override def toString: String =
    ((amount / Currency(1, designation).amount)
      formatted ("%." + decimals(Currency(1, designation).amount) + "f")
      + " " + designation)
}

