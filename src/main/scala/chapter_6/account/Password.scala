package chapter_6.account

trait PasswordProvider{
  val value: String
}

case class Password(value: String) extends PasswordProvider