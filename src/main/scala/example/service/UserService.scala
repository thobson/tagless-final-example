package example.service

import example.domain.User
import example.algebras.UserRepository

object UserService {

  def addUser[F[_]](user: User)(implicit userRepository: UserRepository[F]): F[User] = userRepository.put(user)

}
