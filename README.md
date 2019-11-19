# A Tagless Final example

This is a very basic example illustrating the Tagless Final pattern in Scala. Algebras are defined for a 
`UserRepository` and `OrderRepository`. Each algebra has a corresponding interpreter defined for 
the Cats `Id` and Monix `Task` effect. The `Id` interpreter is implemented using an in memory hashmap and the 
Task interpreter uses Doobie to query a Postgres database.

Please see my associated [blog post](https://www.tobyhobson.com/posts/cats/tagless-final/)

**Note**: This is deliberately simple, to illustrate the Tagless Final pattern. It's not intended to be an example
of a production grade functional program!

### Getting started

1. Take a look at the [algebras](src/main/scala/example/algebras)
2. Take a look at the [services](src/main/scala/example/service) which use the algebras
3. See the inmemory and doobie based [interpreters](src/main/scala/example/interpreters)
4. See how we wire up each interpreter by looking at the [DoobieRunner](src/main/scala/example/DoobieRunner.scala) and
[InMemoryRunner](src/main/scala/example/InMemoryRunner.scala) 