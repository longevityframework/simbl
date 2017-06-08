package simbl.service

import longevity.persistence.Repo
import scala.concurrent.ExecutionContext
import simbl.domain.SimblDomainModel

/** default implementation of service to back the [[BlogPostRoute blog post routes]] */
class BlogPostServiceImpl(
  private val repo: Repo[SimblDomainModel])(
  implicit context: ExecutionContext)
extends BlogPostService {

}
