package finalTask.readers

import finalTask.schemas.{DataFile, ErrorMessages}

import java.io.File
import scala.io._
import scala.reflect.ClassTag

class TxtFileReader[A <: DataFile](rule: Array[String] => A)(implicit ct: ClassTag[A]) extends FileReader[A] {

  override def read(path: String, separator: String): Seq[A] = {
    require(
      fileExists(path),
      ErrorMessages.FileNotExists
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

  private def fileExists(path: String): Boolean = {
    val file = new File(path)
    file.exists()
  }
}
