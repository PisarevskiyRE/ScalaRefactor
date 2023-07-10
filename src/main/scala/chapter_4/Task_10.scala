package chapter_4

object Task_10 extends App{

  trait Converter {
    def convert(value: String): String
  }

  class CountryCodeConverter extends Converter {
    override def convert(value: String): String = {
      value match {
        case "RU" => "Russia"
        case "CA" => "Canada"
        case _ => "Unknown"
      }
    }
  }

  // передаем сервис в качестве параметра метода
  final case class User(id: Int, username: String, password: String, countryCode: String) {
    def getCountry(converter: Converter): String = {
      val countryName = converter.convert(this.countryCode)
      countryName
    }
  }
}
