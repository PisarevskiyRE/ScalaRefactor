package chapter_4

object Task_15 extends App{


  sealed trait LengthUnit{
    val factor: Double
    val label: String
  }
  case object Meter extends LengthUnit {
    override val factor: Double = 1.0
    override val label: String = "м"
  }
  case object Kilometer extends LengthUnit {
    override val factor: Double = 1000.0
    override val label: String = "км"
  }

  case class Length private(value: Double, unit: LengthUnit){
    override def toString: String = s"${value.toString}${unit.label}"
  }

  object Length{
    def apply(value: Double, unit: LengthUnit): Length = {
      require(
        value > 0 && unit.factor > 0,
        "Value and factor must be greater to 0"
      )

      new Length(value, unit)
    } ensuring(_.value > 0 , "Double overflow")
  }


  final class LengthConverter {

    def convert(value: Length, to: LengthUnit) = {
      require(
        to.factor > 0,
        "Factor must be greater to 0"
      )

      if (value.unit == to) value
      else {
        val convertedValue: Double = value.value * value.unit.factor / to.factor
        Length(convertedValue, to)
      }

    } // проверять итоговое значение нет смысл так как произойдет проверка в apply
  }


  val converter = new LengthConverter
  val len100m = Length(100.0, Meter)
  val len100km = Length(100.0, Kilometer)


  val result1 = converter.convert(len100m, Kilometer)
  val result2 = converter.convert(len100m, Meter)
  val result3 = converter.convert(len100km, Kilometer)
  val result4 = converter.convert(len100km, Meter)


  println(s"Result: ${len100m} to ${Kilometer.label} = $result1")
  println(s"Result: ${len100m} to ${Meter.label} = $result2")
  println(s"Result: ${len100km} to ${Kilometer.label} = $result3")
  println(s"Result: ${len100km} to ${Meter.label} = $result4")

  /*
  Result: 100.0м to км = 0.1км
  Result: 100.0м to м = 100.0м
  Result: 100.0км to км = 100.0км
  Result: 100.0км to м = 100000.0м
  */
}
