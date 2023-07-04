package chapter_3

import java.sql.Date
import java.util.UUID

object Task7 extends App{

  case class Bank(
                  name: String,
                  address: Address,
                  email: Email,
                  clients: Seq[Customer],
                  yearOfEstablishment: Int
                 ) {
    
    def bankEmail(): String = email.toString

    def customerEmail(): String = clients.map(x => x.email.toString).mkString(", ")

    def customer = clients.map(x => x.toString).mkString("\n")

    def established() = println(s"$name Established in $yearOfEstablishment.")
  }


  case class Address(city: String, country: String)


  case class Customer private(name: String, lastName: String, email: Email, dateOfBirth: Date, id: UUID = UUID.randomUUID()) {
    override def toString: String = s"[$id]: $name,$lastName,${email.toString()}"
  }

  object Customer {
    def apply(name: String, lastName: String, email: Email, dateOfBirth: String): Customer = new Customer(name, lastName, email, Date.valueOf(dateOfBirth))
  }


  case class Email private(userName: String, domainName: String) {
    override def toString: String = s"$userName@$domainName"
  }

  object Email {
    def apply(email: String) = {
      val strSplitted: Array[String] = email.split("@")

      new Email(strSplitted(0), strSplitted(1))
    }
  }

  val bank1 = Bank(
    "bank1",
    Address("1","1"),
    Email("c1@c1.com"),
    Seq(
      Customer("a1", "a2", Email("a@a.com"),"1989-04-01"),
      Customer("b1", "b2", Email("b@b.com"),"1989-04-01")),
    2023
  )

  println(bank1.bankEmail)
  println(bank1.customerEmail)
  println(bank1.customer)
  bank1.established

  /*
  c1@c1.com
  a@a.com, b@b.com
  [1d7dd78a-81d7-425f-a8db-da1d1713db5f]: a1,a2,a@a.com
  [cd97deaa-5a08-42dd-8ce0-1c0be6926973]: b1,b2,b@b.com
  bank1 Established in 2023.
  */
}



