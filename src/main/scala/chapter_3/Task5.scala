package chapter_3

object Task5 extends App{

  trait AnyName{
    def format: String
  }

  case class Name(str: String, firstSymbolIsUpper: Boolean) extends AnyName{
    override def format: String = {
      firstSymbolIsUpper match {
        case true => str.substring(0, 1).toUpperCase() + str.substring(1, str.length())
        case false => str
      }
    }
  }

  case class Surname(str: String, firstSymbolIsUpper: Boolean) extends AnyName {
    override def format: String = {
      firstSymbolIsUpper match {
        case true => str.substring(0, 1).toUpperCase() + str.substring(1, str.length())
        case false => str
      }
    }
  }

  case class User(id: Int, name: AnyName, surname: AnyName)

  object User{
    def apply(id: Int, name: String, surname: String) =
      new User(
        id,
        Name(name, true),
        Surname(surname, true)
    )
  }

  implicit class UserOps(user: User){
    def fullName(): String = user.name.format + " " + user.surname.format
  }

  println(
      User(1,"bob", "bobowith").fullName
  )

}
