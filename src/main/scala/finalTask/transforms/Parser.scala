package finalTask.transforms

import finalTask.schemas._

trait Parser{
  def pars[A <: DataFile,B <: DataStract](from: A): B
}

class ParserProvider(currencyList: Set[String]) extends Parser{
  override def pars[A <: DataFile, B <: DataStract](from: A): B = {
    val currencyFactory = new CurrencyFactory()
    val currency = currencyFactory.createCurrency(from.currency, from.amount, currencyList)
    Expenses(from.id, from.date, from.expense_name, from.expense_category, currency).asInstanceOf[B]
  }
}

class CurrencyFactory {
  def createCurrency(designation: String, amount: Double, currencyList: Set[String]): Currency = {
    // Здесь можно добавить проверку на неизвестные обозначения валют
    Currency(amount, designation)
  }
}