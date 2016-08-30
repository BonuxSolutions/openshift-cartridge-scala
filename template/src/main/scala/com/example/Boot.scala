package com.example

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")

  // create and start our service actor
  val service = system.actorOf(Props[MyServiceActor], "demo-service")
  val interface = Option(System.getenv("OPENSHIFT_SCALA_IP")) getOrElse "localhost"
  val port = Option(System.getenv("OPENSHIFT_SCALA_PORT")).map(_.toInt) getOrElse 8080

  // start a new HTTP server on OPENSHIFT IP and PORT with our service actor as the handler
  IO(Http) ! Http.Bind(service, interface = interface, port = port)
}
