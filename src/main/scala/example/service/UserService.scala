package example.service

import example.algebras.UserRepository
import example.domain.User

object UserService {

  def addUser[F[_]](user: User)(implicit userRepository: UserRepository[F]): F[User] = userRepository.put(user)

}
