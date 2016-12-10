package simbl

import akka.actor.ActorSystem
import longevity.context.LongevityContext
import simbl.api.BlogRoute
import simbl.api.BlogPostRoute
import simbl.api.UserRoute
import simbl.domain.Blog
import simbl.domain.BlogPost
import simbl.domain.SimblDomainModel
import simbl.domain.User
import simbl.service.BlogServiceImpl
import simbl.service.BlogPostServiceImpl
import simbl.service.UserServiceImpl

/** default container for all the Simble application components */
class SimblContextImpl extends SimblContext {
  private val longevityContext = LongevityContext(SimblDomainModel)
  private val repoPool = longevityContext.repoPool

  val blogRepo = repoPool[Blog]
  val blogPostRepo = repoPool[BlogPost]
  val userRepo = repoPool[User]

  val actorSystem = ActorSystem("SimpleBlogging")
  private implicit val context = actorSystem.dispatcher

  val blogPostService = new BlogPostServiceImpl(blogPostRepo)
  val blogService = new BlogServiceImpl(blogRepo)
  val userService = new UserServiceImpl(userRepo)

  val blogRoute = new BlogRoute(blogService)
  val blogPostRoute = new BlogPostRoute(blogPostService)
  val userRoute = new UserRoute(userService)

}
