package example

import cats.effect.Blocker
import doobie.util.ExecutionContexts
import doobie.util.transactor.Transactor
import example.domain.{Order, User}
import example.interpreters.doobie.{DoobieOrderRepository, DoobieUserRepository}
import example.service.{OrderService, UserService}
import monix.eval.Task

object DoobieMain {

  import monix.execution.Scheduler.Implicits.global
  import scala.concurrent.duration._

  // A transactor that gets connections from java.sql.DriverManager and executes blocking operations
  // on an our synchronous EC. See the chapter on connection handling for more info.
  lazy implicit val tx = Transactor.fromDriverManager[Task](
    "org.postgresql.Driver",  // driver classname
    "jdbc:postgresql:test",   // connect URL (driver-specific)
    "dev",                    // user
    "",                       // password
    Blocker.liftExecutionContext(ExecutionContexts.synchronous) // just for testing
  )

  def go(): Unit = {
    val user = User(1L, "toby.hobson@gmail.com", "Toby", "Hobson")
    val order = Order(1L, user.id, 100, 5)

    // The services need implemtations to be in scope but we also want to
    // call the drop and create methods directly here so we don't simply
    // import the interpreters.doobie._ package
    implicit val userRepository = implicitly[DoobieUserRepository[Task]]
    implicit val orderRepository = implicitly[DoobieOrderRepository[Task]]

    val result = for {
      _ <- userRepository.dropTable
      _ <- userRepository.createTable
      _ <- orderRepository.dropTable
      _ <- orderRepository.createTable

      _ <- UserService.addUser[Task](user)
      _ <- OrderService.createOrder[Task](order)
      orders <- OrderService.findOrders[Task](user.email)
    } yield orders

    /*_*/ result.runSyncUnsafe(5.seconds).iterator.foreach(o => println(o)) /*_*/
  }

}
