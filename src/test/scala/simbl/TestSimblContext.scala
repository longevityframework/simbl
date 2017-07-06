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
trait TestSimblContext extends SimblContext {
  val longevityContext = LongevityContext[Future, SimblDomainModel]()

  val repo = longevityContext.testRepo

  val actorSystem = ActorSystem("SimpleBloggingTest")
  private implicit val context = actorSystem.dispatcher

  // TODO: supply mocks here?

  val blogPostService = new BlogPostServiceImpl(repo)
  val blogService = new BlogServiceImpl(repo)
  val userService = new UserServiceImpl(repo)

  val blogRoute = new BlogRoute(blogService)
  val blogPostRoute = new BlogPostRoute(blogPostService)
  val userRoute = new UserRoute(userService)
}

object TestSimblContext extends TestSimblContext
