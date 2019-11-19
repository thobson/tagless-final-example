package example.interpreters

import _root_.doobie.util.transactor.Transactor
import cats.effect.Bracket

/**
 * "Real" implementations of the algebras using Doobie to store data in a Postgres db
 */
package object doobie {

  implicit def userRepositoryEv[F[_]](implicit tx: Transactor[F], b: Bracket[F, Throwable]) = new DoobieUserRepository[F]
  implicit def orderRepositoryEv[F[_]](implicit tx: Transactor[F], b: Bracket[F, Throwable]) = new DoobieOrderRepository[F]

}
