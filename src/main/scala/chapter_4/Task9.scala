package chapter_4

import java.util.UUID


object Task9 extends App{


  // 1. так как большинство методов возвращают unit и есть var переменные, то предполагаем что это entity(добавляем UID и final case class)
  // 2. Для длинны вполне подайте 0 по умолчанию и см
  // 3. Типы измерений выделяем в отдельный трейт и приписываем коэффициентыц конвертации в см
  // 4. Проверка что значение больше или равно 0
  // 5. Large выделяем в сервис, при этом в конструкотре указываем что такое "Большое"
  // 6. Не уверен но судя по комментарию из параграфа с логером даты мы должны передать сервису уже готовое значение в см


  trait UnitsOfMeasure {
    def conversionFactor: Double
    def convertToCm(value: Double): Double = value * conversionFactor
  }

  object measuredInCm extends UnitsOfMeasure {
    override val conversionFactor: Double = 1.0
  }

  object measuredInMeter extends UnitsOfMeasure {
    override val conversionFactor: Double = 100.0
  }

  object measuredInKm extends UnitsOfMeasure {
    override val conversionFactor: Double = 100000.0
  }


  case class Length(id:UUID, var value: Double, var unit: UnitsOfMeasure) {

    def this() = this(UUID.randomUUID(), 0, measuredInCm)

    private def updateInCm(): Unit = inCm = unit.convertToCm(value)

    var inCm: Double = 0

    def changeValue(value: Double): Unit = {
      if (value >= 0) {
        this.value = value
        updateInCm()
      }
      else throw new IllegalArgumentException("Длинна должна быть >= 0")
    }

    def changeUnit(unit: UnitsOfMeasure): Unit = {
      this.unit = unit
      updateInCm()
    }

    updateInCm()
  }

  class LengthController(value: Double, unit: UnitsOfMeasure) {
    val isLargeInCm = unit.convertToCm(value)

    def isLarge(lengthInCm: Double): Boolean = {
      if (lengthInCm >= isLargeInCm) true
      else false
    }

    override def toString: String = s"``Большое`` это ${isLargeInCm} cm"
  }



  val a = new Length()
  val b = Length(UUID.randomUUID(), 10, measuredInCm)
  val c = Length(UUID.randomUUID(), 10, measuredInKm)

  val lengthController = new LengthController(180, measuredInMeter)

  println(lengthController)


  println(a.inCm)
  println(lengthController.isLarge(a.inCm))
  println(b.inCm)
  println(lengthController.isLarge(b.inCm))
  println(c.inCm)
  println(lengthController.isLarge(c.inCm))


}
