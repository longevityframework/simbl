package simbl.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.json4s.DefaultFormats
import org.json4s.native.Serialization
import simbl.service.BlogService

/** defines the Akka HTTP routes for blog endpoints */
class BlogRoute(
  private val blogService: BlogService) {

  implicit val serialization = Serialization
  implicit val formats = DefaultFormats

  val route: Route = reject  // empty route

}
