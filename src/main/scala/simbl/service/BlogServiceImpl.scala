package simbl.service

import longevity.persistence.Repo
import scala.concurrent.ExecutionContext
import simbl.domain.Blog

/** default implementation of service to back the [[BlogRoute blog routes]] */
class BlogServiceImpl(
  private val blogRepo: Repo[Blog])(
  implicit context: ExecutionContext)
extends BlogService {

}
