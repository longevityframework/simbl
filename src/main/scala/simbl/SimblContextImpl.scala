package simbl

import akka.actor.ActorSystem
import longevity.context.LongevityContext
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import simbl.api.BlogRoute
import simbl.api.BlogPostRoute
import simbl.api.UserRoute
import simbl.domain.SimblDomainModel
import simbl.service.BlogServiceImpl
import simbl.service.BlogPostServiceImpl
import simbl.service.UserServiceImpl

/** default container for all the Simble application components */
class SimblContextImpl extends SimblContext {
  private val longevityContext = LongevityContext[Future, SimblDomainModel]()

  val repo = longevityContext.repo

  val actorSystem = ActorSystem("SimpleBlogging")
  private implicit val context = actorSystem.dispatcher

  val blogPostService = new BlogPostServiceImpl(repo)
  val blogService = new BlogServiceImpl(repo)
  val userService = new UserServiceImpl(repo)

  val blogRoute = new BlogRoute(blogService)
  val blogPostRoute = new BlogPostRoute(blogPostService)
  val userRoute = new UserRoute(userService)

}
