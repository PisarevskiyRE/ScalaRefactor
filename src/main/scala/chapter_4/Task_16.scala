package chapter_4


import java.sql.Date
import java.time.{LocalDate, Period}


object Task_16 extends App{

  case class User private(name: String, birthDate: Date)
  object User{
    def apply(name: String, birthDate: Date): User = {
      require(
        name.length > 3,
        "Name must be > 3 chars"
      )
      require(
        // здесь наверное нельзя ставить сразу проверку на 18,
        // так как может этот пользователь сможет получить доступ
        // в каком нибудь другой части сервиса например для подростков итд
        getAge(birthDate) > 0 && getAge(birthDate) < 120,
        "age must be > 0 and < 120"
      )
      new User(name, birthDate)
    }

    def getAge(birthDate: Date): Int = {
      Period
        .between(
          birthDate.toLocalDate,
          LocalDate.now()
        )
        .getYears
    }
  }


  sealed trait GrantedAccess

  case object Granted extends GrantedAccess{
    override def toString: String = "access granted"
  }
  case object Denied extends GrantedAccess{
    override def toString: String = "access denied"
  }

  object RequireToAccess{
    val maxUserAge = 120
    val minUserAge = 18
    val minNameLen = 10

    // подумал может это не плохо будет что мы создадим в отдельном объекте
    // нужные проверки и потом просто будем их "примешивать"
    // я из-за незнания наверное велосипед придумываю:)

    def ageRequirement(user: User): Boolean = {
      val age = User.getAge(user.birthDate)
      age >= minUserAge && age < maxUserAge
    }

    // для наглядности
    def nameRequirement(user: User): Boolean = {
      user.name.length >= minNameLen
    }
  }


  trait Credentials{
    def checkAccessToUser(user: User): AccessToken
  }


  case class AccessToken(user: User, access: GrantedAccess){
    override def toString: String = s"${user.name} -> ${access}"
  }


  // правила для сервиса передаем при создании, пользователя проверяям в методе
  case class AccessController private(rules: Seq[User => Boolean] ) extends Credentials{

    override def checkAccessToUser(user: User): AccessToken = {
      val resultAccess: Boolean = rules.forall(req => req(user))

      resultAccess match {
        case true => AccessToken(user, Granted)
        case _ => AccessToken(user, Denied)
      }
    }

  }
  object AccessController {
    def apply(rules: Seq[User => Boolean] ) = new AccessController(rules)
  }



  val user1 = User("Bob Bobowich", Date.valueOf("1989-04-16"))

  val controller = AccessController(
    Seq(
      RequireToAccess.ageRequirement,
      RequireToAccess.nameRequirement
    )
  )

  println(
    controller.checkAccessToUser(user1)
  )



}
