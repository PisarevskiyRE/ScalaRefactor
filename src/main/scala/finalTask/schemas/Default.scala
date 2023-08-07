package finalTask.schemas

import finalTask.transforms.Converter

trait DefaultValues {
  val TopCount: Int
  val CurrencyList: Set[CurrencyCode.CurrencyName]
  implicit val DefaultCurrency: CurrencyCode.CurrencyName
  val Separetor: String
}

object Default extends DefaultValues {
  val TopCount: Int = 10
  val CurrencyList: Set[CurrencyCode.CurrencyName] = Converter.exchangeRate.keySet
  implicit val DefaultCurrency: CurrencyCode.CurrencyName = CurrencyCode.Rub
  val Separetor = ","
}
