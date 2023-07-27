package chapter_6.hash

trait HashProvider {
  def hash(value: String): String
}


class Hasher extends HashProvider {
  override def hash(value: String): String = {
    "$2a$10$8K1p/a0dL1LXMIgoEDFrwO"
  }
}

