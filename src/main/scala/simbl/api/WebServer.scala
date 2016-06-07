package simbl.api

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import simbl.SimblContext
import simbl.SimblContextImpl

/** the main program that runs the Simple Blogging web server */
object WebServer extends App {

  val context: SimblContext = new SimblContextImpl

  implicit val system = context.actorSystem
  implicit val materializer = ActorMaterializer()

  val route: Route = context.userRoute.route

  val host = system.settings.config.getString("http.host")
  val port = system.settings.config.getInt("http.port")
  Http().bindAndHandle(route, host, port)
  
}
