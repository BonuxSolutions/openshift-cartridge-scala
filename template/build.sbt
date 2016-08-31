import Dependencies._

name := "the-code"

lazy val commonSettings = Seq(
  organization := "com.bonuxsolutions",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.8",
  resolvers ++= Seq(
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "dnvriend at bintray" at "http://dl.bintray.com/dnvriend/maven",
    "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
    "The New Motion" at "http://nexus.thenewmotion.com/content/groups/public/"
  ),
  libraryDependencies ++= Seq(
    Libraries.scalaLogging,
    Libraries.logback,
    Libraries.scalaTest,
    Libraries.mockito
  ),
  scalacOptions ++= Seq("-feature")
)

lazy val api = (project in file("./api"))
  .settings(commonSettings: _*)

lazy val frontend = (project in file("./frontend"))
  .settings(commonSettings: _*)
  .dependsOn(api)

lazy val backend = (project in file("./backend"))
  .settings(commonSettings: _*)
  .dependsOn(api)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .aggregate(frontend, backend)
