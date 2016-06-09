package simbl

import akka.actor.ActorSystem
import longevity.persistence.Repo
import simbl.api.UserRoute
import simbl.domain.Blog
import simbl.domain.BlogPost
import simbl.domain.User
import simbl.service.UserService

/** an API for a container for all the Simble application components */
trait SimblContext {
  val actorSystem: ActorSystem
  val blogRepo: Repo[Blog]
  val blogPostRepo: Repo[BlogPost]
  val userRepo: Repo[User]
  val userService: UserService
  val userRoute: UserRoute
}
