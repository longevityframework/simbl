package simbl

import longevity.context.LongevityContext
import longevity.context.Mongo
import longevity.persistence.Repo
import simbl.domain.Blog
import simbl.domain.BlogPost
import simbl.domain.SimblCoreDomain
import simbl.domain.User

/** default container for all the Simble application components */
trait SimblContextImpl extends SimblContext {
  val coreDomain = new SimblCoreDomain
  val longevityContext = LongevityContext(coreDomain, Mongo)

  val blogRepo = longevityContext.repoPool[Blog]
  val blogPostRepo = longevityContext.repoPool[BlogPost]
  val userRepo = longevityContext.repoPool[User]
}
