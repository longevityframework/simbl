package simbl

import akka.actor.ActorSystem
import longevity.persistence.Repo
import simbl.api.BlogRoute
import simbl.api.BlogPostRoute
import simbl.api.UserRoute
import simbl.domain.SimblDomainModel
import simbl.service.BlogService
import simbl.service.BlogPostService
import simbl.service.UserService

/** an API for a container for all the Simble application components */
trait SimblContext {
  val actorSystem: ActorSystem

  val repo: Repo[SimblDomainModel]

  val blogPostService: BlogPostService
  val blogService: BlogService
  val userService: UserService

  val blogRoute: BlogRoute
  val blogPostRoute: BlogPostRoute
  val userRoute: UserRoute

}
