package com.bonuxsolutions

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging

object Boot
  extends App
    with LazyLogging
    with MyRoutes {

  // start a new HTTP server on OPENSHIFT IP and PORT with our service actor as the handler
  val interface = Option(System.getenv("OPENSHIFT_SCALA_IP")) getOrElse "localhost"
  val port = 8081 //Option(System.getenv("OPENSHIFT_SCALA_PORT")) map (_.toInt) getOrElse 8080

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()

  val bindingFuture = Http().bindAndHandle(route, interface, port)

  logger.info(s"Server online at http://$interface:$port/\nPress RETURN to stop...")
}
