package finalTask.transforms

import finalTask.schemas.CurrencyCode.CurrencyCode
import finalTask.schemas._

trait Parser{
  def pars[A <: DataFile,B <: DataStruct](from: A): B
}

class DefaultParser(currencyList: Set[CurrencyCode.CurrencyName]) extends Parser{
  override def pars[A <: DataFile, B <: DataStruct](from: A): B = {
    val currency = Currency.createCurrency(from.currency, from.amount, currencyList)
    Expenses(from.id, from.date, from.expense_name, from.expense_category, currency).asInstanceOf[B]
  }
}
