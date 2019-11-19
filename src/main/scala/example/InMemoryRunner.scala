package example

import example.domain.{Order, User}
import example.service.{OrderService, UserService}

object InMemoryRunner {

  // Allows us use for comprehension to flatMap over a generic effect (given a Monad implementation)
  import cats.syntax.flatMap._
  // We need implementations for our chosen effect (Id)
  import example.interpreters.inmemory._

  def go(): Unit = {
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
