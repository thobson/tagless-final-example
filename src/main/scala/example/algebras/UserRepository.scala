package example.algebras

import example.domain.User

trait UserRepository[F[_]] {

  def get(id: Long): F[Option[User]]
  def findByEmail(email: String): F[Option[User]]
  def put(user: User): F[User]

}
