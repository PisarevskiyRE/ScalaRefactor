package Chapter_2

object Task2 extends App{

  class Person(
                age: Int,
                weight: String,
                height: String,
                isActive: Boolean = true) {

    def showCalorieNeeds(): Double = {

      val weightAsInt = getNumberFromString(this.weight)
      val heightAsInt = getNumberFromString(this.height)

      val bmr = calculateCalorieNeeds(age, weightAsInt, heightAsInt)

      bmr * getPersonActiveFactor(this.isActive)
    }

    private def getPersonActiveFactor(isActive: Boolean): Double = if (isActive) 1.375 else 1.2

    private def getNumberFromString(expr: String): Int = expr.split(" ")(0).toInt

    private def calculateCalorieNeeds(
                                       age: Int,
                                       weight: Int,
                                       height: Int): Double = {
      66 + (13.7 * weight) + (5 * height) - (6.8 * age)
    }
  }

  val caloriesForPerson: Double = new Person(40, "72 kg","173 cm").showCalorieNeeds()
  println(caloriesForPerson)

}
