package finalTask.readers

import finalTask.schemas.DataFile

import java.io.File
import scala.io._
import scala.reflect.ClassTag

class TxtReader[A <: DataFile](rule: Array[String] => A)(implicit ct: ClassTag[A]) extends FileReader[A] {

  override def read(path: String): Seq[A] = {
    require(
      fileIsExists(path),
      "Файл не найден"
    )
    require(
      linesCount(path) >= 1,
      "В файле нет строк"
    )

    val file = Source.fromFile(path)
    val fileLines = file.getLines()

    val dataObjects =
      fileLines.map(
        line => rule(
          line.split(",")
        )
      ).toSeq

    file.close()
    dataObjects
  }


  private def fileIsExists(path: String): Boolean = {
    val file = new File(path)
    file.exists()
  }

  private def linesCount(path: String): Int = {
    val file = Source.fromFile(path)
    val fileLines = file.getLines()
    fileLines.size
  }
}
