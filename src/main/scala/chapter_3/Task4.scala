package chapter_3

import java.sql.Date

object Task4 extends App {

  /*
    Не очень понятно из задания что нужно сделать
    И как понимать границы что это уже перебор или еще нет? :)
   */

  case class Person(val name: String, val dob: Date)

  implicit class PersonOps(val person: Person){

    private def personName(): String = s"Name: ${person.name}"
    private def personDetails(): String = s"Details: Name: ${person.name} \n,dob: ${person.dob.toString}"


    def collect: String =  personName() +"\n" +  personDetails()
  }

  val bob = new Person("Bob", new Date(89,3,16))

  println(bob.collect)

}
