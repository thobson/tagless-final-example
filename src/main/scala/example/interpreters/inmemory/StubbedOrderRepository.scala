package example.interpreters.inmemory

import cats.Id
import example.algebras.OrderRepository
import example.domain.Order

import scala.collection.concurrent.TrieMap

object StubbedOrderRepository extends OrderRepository[Id] {

  // Concurrent hash map
  val orders = TrieMap.empty[Long, Order]

  override def get(id: Long): Id[Option[Order]] = orders.get(id)
  override def getByUser(userId: Long): Id[Seq[Order]] = orders.valuesIterator.filter(_.userId == userId).toSeq
  override def put(order: Order): Id[Order] = { orders.put(order.id, order); order }

}
