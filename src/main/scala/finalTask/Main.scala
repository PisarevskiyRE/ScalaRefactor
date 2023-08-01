package finalTask

import finalTask.readers.TxtReader
import finalTask.schemas._
import finalTask.transforms._


object Main extends App{

  object Default{
    val DefaultCurrency = "Rub"
  }


  val txtReader = new TxtReader[ExpensesFromFile](FileRule.f)

  val testData: Seq[ExpensesFromFile] = txtReader.read("src/main/resources/expenses.txt")

  val currencyList: Set[String] = Set("Rub","Usd","Eur")

  val parserProvider = new ParserProvider(currencyList)


  val convertedData = testData.map(
    x => parserProvider.pars[ExpensesFromFile, Expenses](x)
  )

  convertedData.foreach(println)

  val sumAll: Seq[Expenses] => SingleResult = {
    x =>
      SingleResult(
       x.map(x=>x.currency).reduce(_ + _).toString
      )
  }


  val test2 = MetricFactory.Calculate[Expenses, SingleResult](convertedData, sumAll)

  println(test2)

}
