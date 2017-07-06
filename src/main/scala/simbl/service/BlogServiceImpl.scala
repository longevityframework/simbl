package simbl.service

import longevity.persistence.Repo
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import simbl.domain.SimblDomainModel

/** default implementation of service to back the [[BlogRoute blog routes]] */
class BlogServiceImpl(
  private val repo: Repo[Future, SimblDomainModel])(
  implicit context: ExecutionContext)
extends BlogService {

}
