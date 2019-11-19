package example.interpreters.doobie

import cats.effect.Bracket
import doobie.implicits._
import doobie.util.transactor.Transactor
import example.algebras.UserRepository
import example.domain.User

class DoobieUserRepository[F[_]](implicit tx: Transactor[F], b: Bracket[F, Throwable]) extends UserRepository[F] {

  def dropTable = sql"DROP TABLE IF EXISTS users".update.run.transact(tx)

  def createTable =
    sql"CREATE TABLE users(id BIGINT, email VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255))".update.run.transact(tx)

  def put(user: User) =
    sql"INSERT INTO users(id, email, first_name, last_name) VALUES (${user.id}, ${user.email}, ${user.firstName}, ${user.lastName})"
      .stripMargin.update.run.map(_ => user).transact(tx)


  override def get(id: Long) =
    sql"""SELECT id, email, first_name, last_name FROM users""".query[User].option.transact(tx)

  def findByEmail(email: String) =
    sql"""SELECT id, email, first_name, last_name FROM USERS WHERE email = $email""".query[User].option.transact(tx)

}
