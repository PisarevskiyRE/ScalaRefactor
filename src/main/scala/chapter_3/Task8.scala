package chapter_3

import chapter_3.Task8.StatusOrder

object Task8 extends App {

/*
  case class Product(id: Int, isInStock: Boolean, price: Int)
  case class Customer(id: Int, balance: Int, paymentMethod: Any)
  var orderPlaced = false
  def placeOrder(product: Product, customer: Customer): Unit = {
    if (product.isInStock) {
      if (customer.paymentMethod == null) {
        println("Order can not be placed")
      }
      else {
        if (customer.balance < product.price) {
          println("Order can not be placed")
        }
        else {
          orderPlaced = true
        }
      }
    } else {
      println("Order can not be placed")
    }
  }
*/


  case class Product(id: Int, name: String, price: Int)

  case class Stock(id: Int, name: String, productList: Seq[Product])

  case class PaymentMethod(id: Int, name: String)

  case class Customer(id: Int, balance: Int, paymentMethod: PaymentMethod){
    require(balance > 100, !paymentMethod.id.isNaN ) //??? не уверен что так делают
  }

  sealed trait StatusOrder {
    def id: Int
    def name: String
    def isOk: Boolean
    def print: Unit
  }

  case object Status_Ok extends StatusOrder {
    val id: Int = 1
    val name: String = "Ok"
    val isOk: Boolean = true

    override def toString: String = "Status_Ok"

    override def print: Unit = println(this.toString)
  }

  case object Status_MissInStock extends StatusOrder {
    val id: Int = 2
    val name: String = "MissInStock"
    val isOk: Boolean = false

    override def toString: String = "Status_MissInStock"

    override def print: Unit = println(this.toString)
  }

  case object Status_MissPaymentMethod extends StatusOrder {
    val id: Int = 3
    val name: String = "MissPaymentMethod"
    val isOk: Boolean = false

    override def toString: String = "Status_MissPaymentMethod"

    override def print: Unit = println(this.toString)
  }

  case object Status_NotEnoughCash extends StatusOrder {
    val id: Int = 4
    val name: String = "NotEnoughCash"
    val isOk: Boolean = false

    override def toString: String = "Status_NotEnoughCash"

    override def print: Unit = println(this.toString)
  }

  case class Order private(product: Product, customer: Customer, stock: Stock)


  object Order {

    def apply(product: Product, customer: Customer, stock: Stock): Either[StatusOrder, Order] = {

      new Order(product, customer, stock)

      existsProductInStock(product, stock) match {

        case Status_Ok => checkPaymentMethod(customer) match {

          case Status_Ok => checkCustomerBalance(customer, product.price) match {

            case Status_Ok => {
              Status_Ok.print
              Right(new Order(product, customer, stock))
            }

            case other_Status: StatusOrder => {
              other_Status.print
              Left(other_Status)
            }
          }
          case other_Status: StatusOrder => {
            other_Status.print
            Left(other_Status)
          }
        }
        case other_Status: StatusOrder => {
          other_Status.print
          Left(other_Status)
        }
      }
    }


    def existsProductInStock(product: Product, stock: Stock): StatusOrder = {
      if (stock.productList.filter(x=>x.id == product.id).isEmpty) Status_MissInStock
      else Status_Ok
    }

    def checkPaymentMethod(customer: Customer): StatusOrder = {
      if (customer.paymentMethod == null) Status_MissPaymentMethod
      else Status_Ok
    }

    def checkCustomerBalance(customer: Customer, price: Int): StatusOrder = {
      if (customer.balance < price) Status_NotEnoughCash
      else Status_Ok
    }
  }


  val product1 =  Product(1, "p1", 100)
  val product2 =  Product(2, "p2", 100)
  val product3 =  Product(3, "p3", 100)
  val stock1 = Stock(1, "s1", Seq(product1, product2))


  val paymentMethod1 = PaymentMethod(1, "pm1")
  val customer1 = Customer(1, 1000, paymentMethod1)

  val order1: Either[StatusOrder, Order] = Order(product1, customer1, stock1)

  println(order1)


  //Status_Ok
  //Right(Order(Product(1,p1,100),Customer(1,1000,PaymentMethod(1,pm1)),Stock(1,s1,List(Product(1,p1,100), Product(2,p2,100)))))

  //Status_MissInStock
  //Left(Status_MissInStock)


}
