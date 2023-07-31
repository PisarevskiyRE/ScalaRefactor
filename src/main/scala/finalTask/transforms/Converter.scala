package finalTask.transforms

object Converter {
  var exchangeRate = Map(
    "USD" -> Map("USD" -> 1.0 , "EUR" -> 0.7596,
      "RUB" -> 1.211 ),
    "EUR" -> Map("USD" -> 1.316 , "EUR" -> 1.0 ,
      "RUB" -> 1.594 ),
    "RUB" -> Map("USD" -> 0.8257, "EUR" -> 0.6272,
      "RUB" -> 1.0 )
  )
}