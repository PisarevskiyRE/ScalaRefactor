package finalTask

import finalTask.readers.TxtReader
import finalTask.schemas.{CurrencyZone, _}



object Main extends App{


  val txtReader = new TxtReader[ExpensesFromFile](FileRule.f)

  val test = txtReader.read("src/main/resources/expenses.txt")

//  test.foreach(
//    x =>
//    Expenses(x.id, x.date,x.expense_name, x.expense_category, US.make( 10) )
//
//
//  )








}
