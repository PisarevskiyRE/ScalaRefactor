package finalTask

import finalTask.readers.TxtFileReader
import finalTask.schemas._
import finalTask.transforms._

import java.time.LocalDate
import scala.collection.MapView


object Main extends App{



  val path = if (args.length > 0) args(0)
             else "src/main/resources/expenses.txt"




  val staticsService = new StaticsService(
        StaticsServiceParameters(
          path,
          StatisticsFunctions.getStatics(),
          FileRule
      )
  )

  staticsService.getResult().foreach(println)


}
