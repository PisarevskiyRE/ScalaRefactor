package chapter_4

object Task_17 extends App{

  object Default{
    val minNameLen = 3
    val minDomainLen = 3
    val countSplitDomain = 2
  }


  case class Email private(userName: String, domainName: String) {
    override def toString: String = s"$userName@$domainName"
  }

  object Email {
    def apply(userName: String, domainName: String) = {
      require(
        userName.length >= Default.minNameLen,
        "Illegal userName"
      )
      require(
        domainName.length >= Default.minDomainLen &&
          domainName.split("\\.").length == Default.countSplitDomain,
        "Illegal domainName"
      )
      new Email(userName, domainName)
    }
  }

  case class User private(username: String, email: Email){
    def showEmail(): String = s"User [$username] email is [${email}]"
  }
  object User{
    def apply(username: String, email: Email): User = {
      require(username.length > 3,
      "Illegal username")

      new User(username, email)
    }
  }

  sealed trait UserRole

  object RoleEditor extends UserRole {
    override def toString: String = "editor"
  }

  object RoleViewer extends UserRole{
    override def toString: String = "viewer"
  }

  object Privileged extends UserRole {
    override def toString: String = "privileged"
  }

  class UserRoleList[A <: UserRole](roleSeq: Seq[A]) {
    def getUserRoles(): Seq[A] = roleSeq
  }
  // Есть предположение что привелегированность можно обозначить свойством
  // И таким образом можно все свести в один класс ролей, но не понятно как работа идет в контексте приложения
  // Может match удобнее будет делать именно с разными классами


  // наверное перемудрил, не знаю как сдалать так что бы работало такое -
  // case class BaseDataEditor(user: User) extends RoleEditor with Privileged
  // и при этом trait содержал метод который проходился по примененным от него самого трейтам и выводил список
  // гуглил нашел какие-то сложные решения с рефлексией, так что остановлюсь наверное на этом


  case class BaseDataEditor(user: User) extends UserRoleList[UserRole](Seq(RoleEditor))
  case class BaseViewer(user: User) extends UserRoleList[UserRole](Seq(RoleViewer))
  case class PrivilegedDataEditor(user: User) extends UserRoleList[UserRole](Seq(RoleEditor, Privileged))
  case class PrivilegedViewer(user: User) extends UserRoleList[UserRole](Seq(RoleViewer, Privileged))



  class RoleController{
    def role(userRole: UserRole): Unit = println(s"user role is [${userRole.toString}]")
  }



  // пример использования
  val user = User("Bob1", Email("bob", "mail.ru"))
  println(user.toString) // User(Bob1,bob@mail.ru)
  println(user.showEmail()) //User [Bob1] email is [bob@mail.ru]
  PrivilegedDataEditor(user).getUserRoles().foreach(println) //editor privileged

}
