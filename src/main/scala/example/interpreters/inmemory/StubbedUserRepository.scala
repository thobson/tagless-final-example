package example.interpreters.inmemory

import cats.Id
import example.domain.User
import example.algebras.UserRepository

import scala.collection.concurrent.TrieMap

object StubbedUserRepository extends UserRepository[Id] {

  val users = TrieMap.empty[Long, User]

  override def get(id: Long): Id[Option[User]] = users.get(id)
  override def findByEmail(email: String): Id[Option[User]] = users.valuesIterator.find(_.email == email)
  override def put(user: User): Id[User] = { users.put(user.id, user); user }

}
