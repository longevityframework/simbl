package simbl.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.json4s.DefaultFormats
import org.json4s.native.Serialization
import simbl.service.BlogPostService

/** defines the Akka HTTP routes for blog endpoints */
class BlogPostRoute(
  private val blogPostService: BlogPostService) {

  implicit val serialization = Serialization
  implicit val formats = DefaultFormats

  val route: Route = reject  // empty route

}
