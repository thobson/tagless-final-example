# A Tagless Final example

This is a very basic example illustrating the Tagless Final pattern in Scala. Algebras are defined for the
a UserRepository and OrderRepository. Each algebra has a corresponding interpreter defined for the Cats Id and Monix Task
effect. The Id interpreter is implemented using an in memory hashmap and the Task uses Doobie to query a Postgres 
database.

**Note**: This is deliberately simple, to illustrate the Tagless Final pattern. It's not intended to be an example
of a production grade functional program!