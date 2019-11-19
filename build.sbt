import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.tobyhobson"
ThisBuild / organizationName := "toby hobson"

lazy val root = (project in file("."))
  .settings(
    name := "tagless-final",
    libraryDependencies ++= Seq(
      catsCore,
      doobieCore,
      doobiePg,
      monix,
      scalaTest % Test
    )
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-language:higherKinds"
)