package simbl

import longevity.context.LongevityContext
import longevity.context.Mongo
import simbl.domain.Blog
import simbl.domain.BlogPost
import simbl.domain.SimblCoreDomain
import simbl.domain.User

/** default container for all the Simble application components */
trait TestSimblContext extends SimblContext {
  val coreDomain = new SimblCoreDomain
  val longevityContext = LongevityContext(coreDomain, Mongo)

  val repoPool = longevityContext.testRepoPool
  val blogRepo = repoPool[Blog]
  val blogPostRepo = repoPool[BlogPost]
  val userRepo = repoPool[User]
}

object TestSimblContext extends TestSimblContext
