import sbt._

object Dependencies {
  lazy val catsCore =  "org.typelevel" %% "cats-core" % "2.0.0"
  lazy val doobieCore = "org.tpolecat" %% "doobie-core" % "0.8.6"
  lazy val doobiePg = "org.tpolecat" %% "doobie-postgres" % "0.8.6"
  lazy val monix = "io.monix" %% "monix" % "3.1.0"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
}
