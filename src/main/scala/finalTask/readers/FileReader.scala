package finalTask.readers

import finalTask.schemas.DataFile

trait FileReader[A <: DataFile]{
  def read(path: String, separator: String): Seq[A]
}
