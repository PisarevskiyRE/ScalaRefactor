package finalTask.schemas

object CurrencyCode extends Enumeration {
  type CurrencyCode = Value

  final case class CurrencyName private(code: String, name: String) extends Val(code)

  implicit def valueToCurrency(v: Value): CurrencyName = v.asInstanceOf[CurrencyName]

  val Rub = CurrencyName("Rub", "Рубль")
  val Eur = CurrencyName("Eur", "Евро")
  val Usd = CurrencyName("Usd", "Доллар")
}

