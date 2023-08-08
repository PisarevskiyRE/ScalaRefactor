package finalTask.schemas

import finalTask.transforms.Converter


// раздел на две сущности так как возможно структура могла сильно отличаться от той что в файле
// какие нибудь значительные преобразование
// то есть сначала сырые потом измененные/дополненные
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
