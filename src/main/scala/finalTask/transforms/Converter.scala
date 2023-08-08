package finalTask.transforms

import finalTask.schemas.CurrencyCode

object Converter {
  var exchangeRate = Map(
    CurrencyCode.Usd  -> Map(CurrencyCode.Usd  -> 1.0 , CurrencyCode.Eur -> 0.7596,
      CurrencyCode.Rub -> 1.211 ),
    CurrencyCode.Eur  -> Map(CurrencyCode.Usd  -> 1.316 , CurrencyCode.Eur -> 1.0 ,
      CurrencyCode.Rub -> 1.594 ),
    CurrencyCode.Rub -> Map( CurrencyCode.Usd -> 0.8257, CurrencyCode.Eur -> 0.6272,
      CurrencyCode.Rub -> 1.0 ),
  )
}