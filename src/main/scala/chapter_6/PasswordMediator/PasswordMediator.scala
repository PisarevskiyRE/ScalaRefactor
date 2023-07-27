package chapter_6.PasswordMediator

import chapter_6.account._
import chapter_6.hash._


trait Mediator{
  def isHashed(password: PasswordProvider): Boolean
  def hashIfNot(password: PasswordProvider): String
}


class PasswordMediator(hasher: HashProvider) extends Mediator{
  def hashIfNot(password: PasswordProvider): String = {
    if (isHashed(password)) password.value else hasher.hash(password.value)
  }
  def isHashed(password: PasswordProvider): Boolean = password.value.startsWith("""$2a$10$""")
}
