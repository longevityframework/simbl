package simbl.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import simbl.SimblContext
import simbl.SimblContextImpl

object WebServer extends App {

  println("hi from Webserver " + new java.util.Date())

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val context: SimblContext = new SimblContextImpl

  val route: Route = context.userApi.route

  implicit val executor = system.dispatcher

  val host = system.settings.config.getString("http.host")
  val port = system.settings.config.getInt("http.port")
  Http().bindAndHandle(route, host, port)
  
}
