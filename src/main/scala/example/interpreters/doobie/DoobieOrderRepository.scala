package example.interpreters.doobie

import cats.effect.Bracket
import doobie.implicits._
import doobie.util.transactor.Transactor
import example.algebras.OrderRepository
import example.domain.Order

/**
 * Note how we can also make the algebra implementation polymorphic on the Effect
 *
 * @param tx
 * @param b
 * @tparam F
 */
class DoobieOrderRepository[F[_]](implicit tx: Transactor[F], b: Bracket[F, Throwable]) extends OrderRepository[F] {

  // The dropTable and createTable operations aren't actually needed to implement the algebras
  def dropTable = sql"DROP TABLE IF EXISTS orders".update.run.transact(tx)

  def createTable =
    sql"CREATE TABLE orders(id BIGINT, user_id BIGINT, sub_total INT, shipping_cost INT)".update.run.transact(tx)

  def put(order: Order) =
    sql"INSERT INTO orders(id, user_id, sub_total, shipping_cost) VALUES (${order.id}, ${order.userId}, ${order.subTotal}, ${order.shippingCost})"
      .update.run.map(_ => order).transact(tx)

  override def get(id: Long) =
    sql"SELECT id, user_id, sub_total, shipping_cost FROM orders".query[Order].option.transact(tx)

  /*_*/
  override def getByUser(userId: Long) =
    sql"SELECT id, user_id, sub_total, shipping_cost FROM orders WHERE user_id = $userId".query[Order].to[Seq].transact(tx)
  /*_*/

}
