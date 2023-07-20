package chapter_5

import scala.io.Source

object Task_18 extends App{



  // У меня все время возникает чувство что использовать свзявание не самая лучшая идея
  // То есть без 5 минут чистый метод получает в какие либо настройки не через параметры
  // Или это нормальная практика? Сделаю через имплиситы

  trait Defaul{
    val separator: String
    val conf: Seq[String]
  }

  implicit object DefaulImpl extends Defaul{
    val separator = "="
    val conf = Seq("user", "password", "port") //какие поля искать и использовать при подключении
  }

  trait Configuration{
    val conf: Map[String, String]
  }


  case class DbConfiguration private(conf: Map[String, String]) extends Configuration
  object DbConfiguration{
    def apply(conf: Map[String, String], default: Defaul): DbConfiguration= {
      require(
        conf.keySet == default.conf.toSeq
        && conf.size == default.conf.length,
        "Illegal input parameter conf"
      )
      new DbConfiguration(conf)
    }
  }


  trait ConfigReader {
    def readConfig(file: String)(implicit default: Defaul): Map[String, String]
  }

  class TxtConfigReader extends ConfigReader {
    override def readConfig(file: String)(implicit default: Defaul): Map[String, String] = {
      Source
        .fromFile(file)
        .getLines()
        .flatMap { line =>
          line.split(default.separator) match {
            case Array(key, value) if default.conf.contains(key) => Some(key -> value)
            case _ => None
          }
        }
        .toMap
    }
  }

  class ConfigProvider(configReader: ConfigReader)(implicit default: Defaul){
    def loadConfig(file: String): DbConfiguration = {

      val configMap = configReader.readConfig(file)
      DbConfiguration(configMap, default)
    }
  }



}
