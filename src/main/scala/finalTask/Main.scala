package finalTask

import finalTask.schemas._

object Main extends App{

  val path = if (args.length > 0) args(0)
             else "src/main/resources/expenses.txt"



  val staticsService = new StaticsService(
        StaticsServiceParameters(
          path,
          StatisticsFunctions.getStatics(),
          DefaultFilters,
          FileRule
      )
  )

  val result = staticsService.getResult()

  result.foreach(println)
}
