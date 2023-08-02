package finalTask.transforms

object Converter {
  var exchangeRate = Map(
    "Usd" -> Map("Usd" -> 1.0 , "Eur" -> 0.7596,
      "Rub" -> 1.211 ),
    "Eur" -> Map("Usd" -> 1.316 , "Eur" -> 1.0 ,
      "Rub" -> 1.594 ),
    "Rub" -> Map("Usd" -> 0.8257, "Eur" -> 0.6272,
      "Rub" -> 1.0 )
  )
}