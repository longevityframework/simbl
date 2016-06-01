package simbl

import longevity.persistence.Repo
import simbl.domain.Blog
import simbl.domain.BlogPost
import simbl.domain.User

/** an API for a container for all the Simble application components */
trait SimblContext {
  val blogRepo: Repo[Blog]
  val blogPostRepo: Repo[BlogPost]
  val userRepo: Repo[User]
}
