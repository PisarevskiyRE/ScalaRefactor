package finalTask

import finalTask.readers.TxtFileReader
import finalTask.schemas._
import finalTask.transforms._


case class StaticsServiceParameters(
                                    path: String,
                                    statisticsFunctions: Map[String, Seq[Expenses] => Result],
                                    fileRule: Rule
                                   )




class StaticsService(staticsServiceParameters : StaticsServiceParameters) {

  private val txtReader = new TxtFileReader[ExpensesFromFile](staticsServiceParameters.fileRule.f)

  private val txtData: Seq[ExpensesFromFile] = txtReader.read(staticsServiceParameters.path, DefaultValues.Separator)

  private val parserProvider = new DefaultParser(DefaultValues.CurrencyList)

  private val convertedData = txtData.map(
    x => parserProvider.pars[ExpensesFromFile, Expenses](x)
  )


  private val result: Seq[Result] = staticsServiceParameters.statisticsFunctions.map { case (name, function) =>
    function match {
      case singleFunc: (Seq[Expenses] => SingleResult) =>
        StatisticsCalculator.Calculate[Expenses, SingleResult](convertedData, singleFunc)

      case multiFunc: (Seq[Expenses] => MultiResult) =>
        StatisticsCalculator.Calculate[Expenses, MultiResult](convertedData, multiFunc)
    }
  }.toSeq


  def getResult(): Seq[Result] = result

}
