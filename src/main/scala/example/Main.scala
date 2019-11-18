package example

import example.domain.{Order, User}
import example.service.{OrderService, UserService}

object Main {

  import cats.syntax.flatMap._
  import example.interpreters.inmemory._

  def main(args: Array[String]): Unit = {
    val user = User(1L, "toby.hobson@gmail.com", "Toby", "Hobson")
    val order = Order(1L, user.id, 100, 5)

    val result = for {
      _ <- UserService.addUser(user)
      _ <- OrderService.createOrder(order)
      orders <- OrderService.findOrders(user.email)
    } yield orders

    /*_*/ result.iterator.foreach(o => println(o)) /*_*/
  }

}
