package chapter_4

import chapter_4.Task_12.CsvReader.FileConfig

import java.io.File


object Task_12 extends App{

  trait FileReader {
    def read(): Unit
  }

  final class CsvReader(config: FileConfig) extends FileReader{
    override def read(): Unit = println(s"Чтение файла [${config.filePath}]")
  }

  object CsvReader{

    final case class FileConfig private(filePath: String, separator: Char, hasHeader: Boolean)

    object FileConfig {

      private def isExists(filePath: String): Boolean = {
        val file = new File(filePath)

        if (!file.exists()) true
        else false
      }

      def apply(fileName: String,  separator: Char = ',', hasHeader: Boolean = true): FileConfig = {
        if (!isExists(fileName: String))
          throw new IllegalArgumentException("Файл не найден!")

        new FileConfig(fileName, separator, hasHeader)
      }
    }
  }
}
