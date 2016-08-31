package com.bonuxsolutions

import akka.http.scaladsl.server.Directives._

trait MyRoutes {

  val route =
    path("") {
      get {
        complete {
          "Say hello to akka-http"
        }
      }
    }
}