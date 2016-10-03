package simbl

import akka.actor.ActorSystem
import longevity.context.LongevityContext
import simbl.api.BlogRoute
import simbl.api.BlogPostRoute
import simbl.api.UserRoute
import simbl.domain.Blog
import simbl.domain.BlogPost
import simbl.domain.SimblSubdomain
import simbl.domain.User
import simbl.service.BlogServiceImpl
import simbl.service.BlogPostServiceImpl
import simbl.service.UserServiceImpl

/** default container for all the Simble application components */
trait TestSimblContext extends SimblContext {
  val coreDomain = new SimblSubdomain
  val longevityContext = LongevityContext(coreDomain)

  val repoPool = longevityContext.testRepoPool
  val blogRepo = repoPool[Blog]
  val blogPostRepo = repoPool[BlogPost]
  val userRepo = repoPool[User]

  val actorSystem = ActorSystem("SimpleBloggingTest")
  private implicit val context = actorSystem.dispatcher

  // TODO: supply mocks here?

  val blogPostService = new BlogPostServiceImpl(blogPostRepo)
  val blogService = new BlogServiceImpl(blogRepo)
  val userService = new UserServiceImpl(userRepo)

  val blogRoute = new BlogRoute(blogService)
  val blogPostRoute = new BlogPostRoute(blogPostService)
  val userRoute = new UserRoute(userService)
}

object TestSimblContext extends TestSimblContext
