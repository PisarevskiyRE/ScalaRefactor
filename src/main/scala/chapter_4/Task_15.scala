package chapter_4

object Task_15 extends App{


  sealed trait LengthUnit

  case object Meter extends LengthUnit
  case object Kilometer extends LengthUnit


  case class Length(value: Double, unit: LengthUnit)

  final class LengthConverter {

    def convert(value: Length, to: LengthUnit) = {
      if (value.unit == Meter) value.value / 1000
      else if (value.unit == Kilometer) value.value * 1000
      else "can't convert"
    }

  }


  val converter = new LengthConverter
  val result = converter.convert(Length(100.0, Meter), Kilometer)
  println(s"Result: 100.0m  = $result km")
}
