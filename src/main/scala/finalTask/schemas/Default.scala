package finalTask.schemas

import finalTask.transforms.Converter

trait Default {
  val TopCount: Int
  val CurrencyList: Set[CurrencyCode.CurrencyName]
  implicit val DefaultCurrency: CurrencyCode.CurrencyName
  val Separator: String
}

object DefaultValues extends Default {
  val TopCount: Int = 10
  val CurrencyList: Set[CurrencyCode.CurrencyName] = Converter.exchangeRate.keySet
  implicit val DefaultCurrency: CurrencyCode.CurrencyName = CurrencyCode.Rub
  val Separator = ","
}
