package chapter_5

import java.util.UUID
import scala.util.Random

object Task_19 extends App {
/*
  генерировать список List[Person], где Person является классом вида case class Person(id: String, age: Int, code: Int)
  длина списка указывается в качестве параметра при вызове функции генерации списка
  значения  age, id - генерируются автоматически
  параметр code может иметь следующие значения: 0, 1 или 2
  code имеет значение 0, если age равен нулю
  code имеет значение 1, если значение age  является нечетным числом
  code имеет значение 2, если значение age  является четным числом, отличным от нуля
  обязательно должна быть предусмотрена возможность вносить изменения, предоставляя дополнительное правило для вычисления значения code, без изменения кода класса PersonGenerator
*/

  object Default {
    val MinPersonAge: Int = 0
    val MaxPersonAge: Int = 100

    val CodeRule: Int => Int =
      _ match {
        case 0 => 0
        case a if a % 2 != 0 => 1
        case a if a % 2 == 0 => 2
      }

    val UuidLen: Int = java.util.UUID.randomUUID().toString.length

    def checkAge(age: Int): Boolean = {
      age >= Default.MinPersonAge && age <= Default.MaxPersonAge
    }
  }

  trait Coder {
    def computeCode(age: Int): Int
  }

  class DefaultCoder private(rule: Int => Int) extends Coder {
    override def computeCode(age: Int): Int = {
      require(
        Default.checkAge(age),
        "Illegal age value"
      )

      rule(age)
    }
  }

  object DefaultCoder {
    def apply(rule: Int => Int): DefaultCoder = new DefaultCoder(rule)
  }

  case class Person(id: String, age: Int, code: Int)

  object Person {
    def apply(id: String, age: Int, code: Int): Person = {
      require(
        id.length == Default.UuidLen &&
          Default.checkAge(age) &&
          code >= 0 && code < 3,
        "Illegal Person params"
      )
      new Person(
        id,
        age,
        code
      )
    }
  }

  trait Generator {
    def generate(): Int
  }

  case class RandomAgeGenerator private(minAge: Int, maxAge: Int) extends Generator {

    import scala.util.Random

    def generate(): Int = Random.nextInt(maxAge - minAge + 1) + minAge
  }

  object RandomAgeGenerator {
    def apply(minAge: Int, maxAge: Int): RandomAgeGenerator = {
      require(
        minAge >= 0 &&
          minAge < maxAge,
        "Illegal parameters"
      )

      new RandomAgeGenerator(minAge, maxAge)
    }
  }

  class PersonGenerator private(coder: Coder, ageGenerator: Generator) {

    def generatePersonList(size: Int): List[Person] = {
      require(
        size > 0,
        "Size must be > 0"
      )
      List.fill(size)(generatePerson())
    }

    private def generatePerson(): Person = {
      val age = ageGenerator.generate()
      val code = coder.computeCode(age)

      Person(
        java.util.UUID.randomUUID().toString,
        age,
        code
      )
    }
  }

  object PersonGenerator {
    def apply(coder: Coder, ageGenerator: Generator): PersonGenerator =
      new PersonGenerator(coder, ageGenerator)
  }

  val defaultCoder = DefaultCoder(Default.CodeRule)
  val ageGenerator = RandomAgeGenerator(Default.MinPersonAge, Default.MaxPersonAge)
  val personGenerator = PersonGenerator(defaultCoder, ageGenerator).generatePersonList(20)

  personGenerator.foreach(println)

}
