package chapter_4

object Task_13 {

  trait Connection{
    def connect(): Unit
    def disconnect(): Unit
  }

  final case class Credentials private(username: String, password: String)

  object Credentials {
    private def isValidUsername(username: String): Boolean = {
      if (username.length <= 2) false
      else
        true
    }

    private def isValidPassword(password: String): Boolean = {
      if (password.length <= 4) false
      else
        true
    }

    def apply(username: String, password: String): Credentials = {
      if (!isValidUsername(username) || !isValidPassword(password))
        throw new IllegalArgumentException("Неподходящий логин или пароль")

      new Credentials(username, password)
    }
  }

  final case class DbUrl private(url: String)

  object DbUrl{
    def apply(url: String): DbUrl = {
      if (!isValidUrl(url))
        throw new IllegalArgumentException("Неподходящий логин или пароль")

      new DbUrl(url)
    }

    private def isValidUrl(url: String): Boolean = {
      //проверки URL базы
      true
    }
  }

  // обычно для DbConnection один раз задают имя\пароль\база а потом многократно переиспользуют,
  // без изменения этих данных, так что оставляю это в конструкторе
  final case class DbConnection(credentials: Credentials, dbUrl: DbUrl) extends Connection {
    override def connect(): Unit = {
      println("Подключение")
    }

    override def disconnect(): Unit = {
      println("Отключение")
    }
  }
}
