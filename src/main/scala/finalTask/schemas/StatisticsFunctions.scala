package finalTask.schemas

import finalTask.schemas
import finalTask.transforms.{MultiResult, Result, SingleResult}

import java.time.LocalDate
import scala.collection.MapView

object StatisticsFunctions {
  import schemas.DefaultValues._

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
        x.sortBy(_.currency.amount)(Ordering[Double].reverse).take(DefaultValues.TopCount)
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


  def getStatics(): Map[String, Seq[Expenses] => Result] = {
    Map(
      "totalExpensesFunc" -> totalExpensesFunc,
      "expensesInCurrentMonthFunc" -> expensesInCurrentMonthFunc,
      "top10ExpensesFunc" -> top10ExpensesFunc,
      "leadingCategoryFunc" -> leadingCategoryFunc,
    )
  }


}
