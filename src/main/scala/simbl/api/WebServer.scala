package simbl.api

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import simbl.SimblContext
import simbl.SimblContextImpl

object WebServer extends App {

  println("hi from Webserver " + new java.util.Date())

  val context: SimblContext = new SimblContextImpl

  implicit val system = context.actorSystem
  implicit val materializer = ActorMaterializer()

  val route: Route = context.userApi.route

  val host = system.settings.config.getString("http.host")
  val port = system.settings.config.getInt("http.port")
  Http().bindAndHandle(route, host, port)
  
}
