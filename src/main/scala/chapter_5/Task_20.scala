package chapter_5

object Task_20 extends App{


  // не уверен когда нужно передавать путь к файлу
  // то есть у меня есть опасения что лучше ридер создавать уже готовый для чтения, то есть передать путь во время создания
  // и тогда получится что то типа -
  // val csv = CsvReader("/path")     уже готовый для работы
  // и тогда трансформеру при создании передать ридер а методу transform недостающую функцию s=>s
  // сомневаюсь как правильно(если я вообще правильно задание понял:)

  trait FileReader {
    def readFile(filePath: String): String
  }

  class CsvReader extends FileReader {
    override def readFile(filePath: String): String = {
     "CSV File"
    }
  }

  class JsonReader extends FileReader {
    override def readFile(filePath: String): String = {
      "Json file"
    }
  }

  trait DataTransformer {
    def transformData(filePath: String, func: String => String): String
  }

  class Transformer(fileReader: FileReader) extends DataTransformer {

    override def transformData(filePath: String, func: String => String): String = {
      val data = fileReader.readFile(filePath)

      func(data)
    }
  }


}
