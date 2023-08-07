package finalTask

import finalTask.readers.TxtFileReader
import finalTask.schemas._
import finalTask.transforms._

import java.time.LocalDate
import scala.collection.MapView


case class StaticsServiceParameters[A <: Result](
                                    path: String,
//                                    statisticsFunctions: Map[String, Seq[Expenses] => A],
                                    fileRule: Rule
                                   )









class StaticsService[A <: Result](staticsServiceParameters : StaticsServiceParameters[A], defaults: DefaultValues ){

  import Default.DefaultCurrency

  private val totalExpensesFunc: Seq[Expenses] => SingleResult = {
    x =>
      SingleResult(
        x.map(y => y.currency).reduce(_ + _).toString
      )
  }

  private val expensesInCurrentMonthFunc: Seq[Expenses] => SingleResult = {
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


  private val top10ExpensesFunc: Seq[Expenses] => MultiResult = {
    x =>
      MultiResult(
        x.sortBy(_.currency.amount)(Ordering[Double].reverse).take(Default.TopCount)
      )
  }

  private val leadingCategoryFunc: Seq[Expenses] => SingleResult = { x =>
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

  val txtReader = new TxtFileReader[ExpensesFromFile](staticsServiceParameters.fileRule.f)
  val txtData: Seq[ExpensesFromFile] = txtReader.read(staticsServiceParameters.path, defaults.Separetor)
  val parserProvider = new DefaultParser(defaults.CurrencyList)

  val convertedData = txtData.map(
    x => parserProvider.pars[ExpensesFromFile, Expenses](x)
  )


  val totalExpenses = StatisticsCalculator.Calculate[Expenses, SingleResult](convertedData, totalExpensesFunc)
  val expensesInCurrentMonth = StatisticsCalculator.Calculate[Expenses, SingleResult](convertedData, expensesInCurrentMonthFunc)
  val top10Expenses = StatisticsCalculator.Calculate[Expenses, MultiResult](convertedData, top10ExpensesFunc)
  val leadingCategory = StatisticsCalculator.Calculate[Expenses, SingleResult](convertedData, leadingCategoryFunc)


  println(totalExpenses)
  println(expensesInCurrentMonth)
  top10Expenses.value.foreach(println)
  println(leadingCategory)


}
