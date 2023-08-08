package finalTask.schemas


trait Filters[A]{
  val filters: Seq[Seq[A] => Seq[A]]
}

object DefaultFilters extends Filters[Expenses]{
  val filters: Seq[Seq[Expenses] => Seq[Expenses]] = Seq(
    _.filter(item => item.date.getYear == 2023),
    _.filter(item => item.expense_category == "Food")

    // собственно пользователь какие угодна может создать свои фильтры унаследовавшись от трейта Filters[A]
    // передав их в сервис
    // не трогая при этом сами метрики
  )
}
