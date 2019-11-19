package example.interpreters

/**
 * In memory implementations of the algebras, using the "Id" effect. i.e. no real effect
 */
package object inmemory {

  implicit val userRepositoryEv = StubbedUserRepository
  implicit val orderRepositoryEv = StubbedOrderRepository

}
