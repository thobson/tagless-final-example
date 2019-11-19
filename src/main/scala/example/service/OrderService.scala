package example.service

import cats.Monad
import cats.data.OptionT
import example.algebras.{OrderRepository, UserRepository}
import example.domain.Order

object OrderService {

  /**
   * This is a trivial example but it demonstrates a few important concepts:
   *
   * 1. The Effect "F" is polymorphic
   * 2. It requires algebra implementations capable of handling the given effect
   * 3. As it sequences the operations it also requires a Monad implementation for F
   *
   * @param email
   * @param M
   * @param userRepository
   * @param orderRepository
   * @tparam F
   * @return
   */
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
