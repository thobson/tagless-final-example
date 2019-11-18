package example.service

import cats.Monad
import cats.data.OptionT
import example.domain.Order
import example.algebras.{OrderRepository, UserRepository}

object OrderService {

  def findOrders[F[_]](email: String)(
    implicit
    M: Monad[F],
    userRepository: UserRepository[F],
    orderRepository: OrderRepository[F]
  ): F[Seq[Order]] = {
    OptionT(
      userRepository.findByEmail(email)
    ).semiflatMap(user =>
      orderRepository.getByUser(user.id)
    ).getOrElse(Seq.empty)
  }

  def createOrder[F[_]](order: Order)(implicit orderRepository: OrderRepository[F]): F[Order] = orderRepository.put(order)

}
