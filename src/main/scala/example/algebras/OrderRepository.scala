package example.algebras

import example.domain.Order

trait OrderRepository[F[_]] {

  def get(id: Long): F[Option[Order]]
  def getByUser(userId: Long): F[Seq[Order]]
  def put(order: Order): F[Order]

}
