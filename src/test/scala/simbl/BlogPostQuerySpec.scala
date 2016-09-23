package simbl

import longevity.test.QuerySpec
import scala.concurrent.ExecutionContext.Implicits.global
import simbl.domain.BlogPost

class BlogPostQuerySpec extends QuerySpec[BlogPost](TestSimblContext.longevityContext) {

  lazy val sample = randomP

  behavior of "BlogPost.queries.recentPosts"
  it should "produce the expected results" in {
    exerciseQuery(BlogPost.queries.recentPosts(sample.blog))
  }

}
