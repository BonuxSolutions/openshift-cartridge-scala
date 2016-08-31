import Dependencies._

name := "api"

libraryDependencies ++= Seq(
  Libraries.play.json
)

parallelExecution in Test := false

fork in Test := true

scalacOptions ++= Seq("-feature",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps")
