package simbl

import longevity.test.QuerySpec
import scala.concurrent.Future
import simbl.domain.BlogPost
import simbl.domain.SimblDomainModel

class BlogPostQuerySpec extends QuerySpec[Future, SimblDomainModel, BlogPost](TestSimblContext.longevityContext) {

  lazy val sample = randomP

  behavior of "BlogPost.queries.recentPosts"
  it should "produce the expected results" in {
    exerciseQuery(BlogPost.queries.recentPosts(sample.blog))
  }

}
