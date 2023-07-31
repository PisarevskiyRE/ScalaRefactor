package finalTask.readers

import finalTask.schemas.DataFile

trait FileReader[A <: DataFile]{
  def read(path: String): Seq[A]
}
