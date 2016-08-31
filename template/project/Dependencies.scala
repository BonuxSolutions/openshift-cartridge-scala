import sbt._

object Dependencies {

  object Libraries {
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0"
    val logback = "ch.qos.logback" % "logback-classic" % "1.1.7"

    val scalaTest = "org.scalatest" %% "scalatest" % "3.0.0" % "test"
    val mockito = "org.mockito" % "mockito-all" % "1.10.19" % "test"

    val levelDb = "org.iq80.leveldb" % "leveldb" % "0.9"
    val levelDbJni = "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8"
    val inMemoryJournal = "com.github.dnvriend" %% "akka-persistence-inmemory" % "1.3.7" % "test"

    object commons {
      val lang3 = "org.apache.commons" % "commons-lang3" % "3.4"
    }

    object google {
      val guava = "com.google.guava" % "guava" % "19.0"
    }

    object akka {
      val version = "2.4.9"
      val actor = "com.typesafe.akka" %% "akka-actor" % version
      val agent = "com.typesafe.akka" %% "akka-agent" % version
      val persistence = "com.typesafe.akka" %% "akka-persistence" % version
      val persistenceQuery = "com.typesafe.akka" %% "akka-persistence-query-experimental" % version
      val clusterSharding = "com.typesafe.akka" %% "akka-cluster-sharding" % version
      val cluster = "com.typesafe.akka" %% "akka-cluster" % version
      val slf4j = "com.typesafe.akka" %% "akka-slf4j" % version
      val stream = "com.typesafe.akka" %% "akka-stream" % version
      val httpCore = "com.typesafe.akka" %% "akka-http-core" % version
      val http = "com.typesafe.akka" %% "akka-http-experimental" % version
      val httpSprayJson = "com.typesafe.akka" %% "akka-http-spray-json-experimental" % version
      val httpXml = "com.typesafe.akka" %% "akka-http-xml-experimental" % version
      val testKit = "com.typesafe.akka" %% "akka-testkit" % version % "test"
      val httpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % version % "test"
    }

    object play {
      val version = "2.5.6"
      val json = "com.typesafe.play" %% "play-json" % version
      val ws = "com.typesafe.play" %% "play-ws" % version
    }

  }

}