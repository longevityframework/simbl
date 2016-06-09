package simbl.service

import longevity.persistence.Repo
import scala.concurrent.ExecutionContext
import simbl.domain.BlogPost

/** default implementation of service to back the [[BlogPostRoute blog post routes]] */
class BlogPostServiceImpl(
  private val blogPostRepo: Repo[BlogPost])(
  implicit context: ExecutionContext)
extends BlogPostService {

}
