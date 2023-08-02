package finalTask.transforms

import finalTask.schemas._

trait Parser{
  def pars[A <: DataFile,B <: DataStruct](from: A): B
}

class ParserProvider(currencyList: Set[String]) extends Parser{
  override def pars[A <: DataFile, B <: DataStruct](from: A): B = {
    val currencyFactory = new CurrencyFactory()
    val currency = currencyFactory.createCurrency(from.currency, from.amount, currencyList)
    Expenses(from.id, from.date, from.expense_name, from.expense_category, currency).asInstanceOf[B]
  }
}

class CurrencyFactory {
  def createCurrency(designation: String, amount: Double, currencyList: Set[String]): Currency = {
    require(
      currencyList.contains(designation),
      s"Данный вид валюты ${designation} отсутсвует в списке доступных"
    )

    Currency(amount, designation)
  }
}