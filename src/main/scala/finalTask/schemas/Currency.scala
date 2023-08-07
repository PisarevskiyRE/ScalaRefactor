package finalTask.schemas

import finalTask.schemas.CurrencyCode.CurrencyCode
import finalTask.transforms.Converter

case class Currency private(amount: Double, designation: CurrencyCode.CurrencyName) {

  def +(that: Currency)(implicit defaultCurrency: CurrencyCode.CurrencyName): Currency = {
    val updatedAmount = {
      if (designation == defaultCurrency)
         this.amount + that.amount
       else
        this.convert(defaultCurrency).amount + that.amount
    }
    Currency(updatedAmount, defaultCurrency)
  }


  def *(x: Double): Currency =
    Currency((this.amount * x), designation)

  def -(that: Currency)(implicit defaultCurrency: CurrencyCode.CurrencyName): Currency = {
    val updatedAmount = {
      if (designation == defaultCurrency)
        this.amount - that.amount
      else
        this.convert(defaultCurrency).amount - that.amount
    }
    Currency(updatedAmount, defaultCurrency)
  }

  def convert(to: CurrencyCode.CurrencyName): Currency = {
    val convertedAmount = this.amount * Converter.exchangeRate(designation)(to)
    Currency(convertedAmount, to)
  }

  private def decimals(n: Double): Int =
    if (n == 1) 0 else 1 + decimals(n / 10)

  override def toString: String =
    ((amount / Currency(1, designation).amount)
      formatted ("%." + decimals(Currency(1, designation).amount) + "f")
      + " " + designation)
}

object Currency{
  def apply(amount: Double, designation: CurrencyCode.CurrencyName): Currency = {
    new Currency(amount,designation)
  }

  def createCurrency(designation: String, amount: Double, currencyList: Set[CurrencyCode.CurrencyName]): Currency = {
    require(
      currencyList.contains(CurrencyCode.withName(designation)),
      s"Данный вид валюты ${designation} отсутсвует в списке доступных"
    )
    Currency(amount, CurrencyCode.withName(designation))
  }
}

