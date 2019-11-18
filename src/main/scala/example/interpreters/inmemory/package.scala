package example.interpreters

package object inmemory {

  implicit val userRepositoryEv = StubbedUserRepository
  implicit val orderRepositoryEv = StubbedOrderRepository

}
