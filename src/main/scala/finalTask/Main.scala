package finalTask


import finalTask.readers.TxtReader
import finalTask.schemas._
import finalTask.transforms._

import java.time.LocalDate
import scala.collection.MapView


object Main extends App{

  object Default{
    val TopCount: Int = 10
    val CurrencyList: Set[String] = Converter.exchangeRate.keySet
    implicit val DefaultCurrency = "Rub"
  }

  import Default.DefaultCurrency


  val path = if (args.length > 0) args(0)
             else "src/main/resources/expenses.txt"


  val txtReader = new TxtReader[ExpensesFromFile](FileRule.f)
  val testData: Seq[ExpensesFromFile] = txtReader.read(path)
  val parserProvider = new ParserProvider(Default.CurrencyList)


  val convertedData = testData.map(
    x => parserProvider.pars[ExpensesFromFile, Expenses](x)
  )



  val totalExpensesFunc: Seq[Expenses] => SingleResult = {
    x =>
      SingleResult(
       x.map(y=>y.currency).reduce(_ + _).toString
      )
  }

  val expensesInCurrentMonthFunc: Seq[Expenses] => SingleResult = {
    val currentDate: LocalDate = LocalDate.now()
    x =>
      SingleResult(
        x.filter(
          y =>
            y.date.getYear == currentDate.getYear
              && y.date.getMonth == currentDate.getMonth
        )
        .length
        .toString
      )
  }


  val top10ExpensesFunc: Seq[Expenses] => MultiResult = {
    x =>
      MultiResult(
        x.sortBy(_.currency.amount)(Ordering[Double].reverse).take(Default.TopCount)
      )
  }

  val leadingCategoryFunc: Seq[Expenses] => SingleResult = { x =>
    SingleResult {
      val expensesByCategory: Map[String, Seq[Expenses]] = x.groupBy(_.expense_category)
      val expensesSumByCategory: MapView[String, Currency] = expensesByCategory.mapValues { expensesForCategory =>
        val totalAmount = expensesForCategory.map(_.currency.amount).sum
        Currency(totalAmount, expensesForCategory.head.currency.designation)
      }
      val leadingCategory: (String, Currency) = expensesSumByCategory.maxBy { case (_, currency) => currency.amount }

      leadingCategory.toString()
    }
  }


  val totalExpenses = MetricFactory.Calculate[Expenses, SingleResult](convertedData, totalExpensesFunc)
  val expensesInCurrentMonth = MetricFactory.Calculate[Expenses, SingleResult](convertedData, expensesInCurrentMonthFunc)
  val top10Expenses = MetricFactory.Calculate[Expenses, MultiResult](convertedData, top10ExpensesFunc)
  val leadingCategory = MetricFactory.Calculate[Expenses, SingleResult](convertedData, leadingCategoryFunc)


  println(totalExpenses)
  println(expensesInCurrentMonth)
  top10Expenses.value.foreach(println)
  println(leadingCategory)


}
