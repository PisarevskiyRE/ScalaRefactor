package chapter_5

object Task_22 {

  trait HasherInterface {
    def hash(password: PasswordInterface): String
  }

  class Hasher extends HasherInterface {
    def hash(password: PasswordInterface): String = {
      "$2a$10$8K1p/a0dL1LXMIgoEDFrwO"
    }
  }

  trait PasswordInterface {
    def isHashed(password: Password): Boolean

    def hashIfNot(password: Password): String
  }


  class Password(hasher: HasherInterface, value: String) extends PasswordInterface {
    def isHashed(password: Password): Boolean = password.value.startsWith("""$2a$10$""")

    def hashIfNot(password: Password): String = {
      if (isHashed(password)) password.value else hasher.hash(password)
    }
  }




  /*
  package hash

  import account.Password

  class Hasher {
    def hash(password: Password): String = {
      "$2a$10$8K1p/a0dL1LXMIgoEDFrwO"
    }
  }


  package account

  import hash.Hasher

  class Password(hasher: Hasher, value: String) {
    def isHashed(password: Password): Boolean = password.value.startsWith("""$2a$10$""")

    def hashIfNot(password: Password): String = {
      if (isHashed(password)) password.value else hasher.hash(password)
    }
  }

  */




}
