import Dependencies._

name := "backend"

libraryDependencies ++= Seq(
  Libraries.akka.actor,
  Libraries.akka.agent,
  Libraries.akka.persistence,
  Libraries.akka.persistenceQuery,
  Libraries.akka.stream,
  Libraries.akka.slf4j,
  Libraries.akka.testKit,
  Libraries.akka.agent,
  Libraries.play.json,
  Libraries.akka.httpCore,
  Libraries.akka.http,
  Libraries.levelDb % "test",
  Libraries.levelDbJni % "test"
)

parallelExecution in Test := false

fork in Test := true

scalacOptions ++= Seq("-feature",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps")

mainClass in Compile := Some("com.bonuxsolutions.Boot")
