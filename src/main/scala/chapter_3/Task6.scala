package chapter_3

import java.util.UUID

object Task6 extends App{


  class Bank(
              val name: String,
              val city: String,
              val country: String,
              value: String,
              domain: String,
              val nameOfTheCustomer: String,
              val lastNameOrSurname: String,
              valueForCustomerEmail: String,
              domainForCustomerEmail: String,
              val dateOfBirth: String= "") {
    val idOfTheCustomer: UUID = UUID.randomUUID()

    def bankEmail(): String = s"$value@$domain"

    def customerEmail(): String = s"$value@$domain"

    def customer: String = s"[$idOfTheCustomer]: $name,$lastNameOrSurname,${customerEmail()}"

    println(s"$name Established in 2023.")

  }
}
