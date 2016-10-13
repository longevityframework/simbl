package simbl.domain

import longevity.subdomain.PType
import longevity.subdomain.Persistent
import longevity.subdomain.query.Query
import org.joda.time.DateTime

case class BlogPost(
  uri: BlogPostUri,
  title: String,
  slug: Option[Markdown] = None,
  content: Markdown,
  labels: Set[String] = Set(),
  postDate: DateTime,
  blog: BlogUri,
  authors: Set[Username])
extends Persistent

object BlogPost extends PType[BlogPost] {

  object props {
    val uri = prop[BlogPostUri]("uri")
    val blog = prop[BlogUri]("blog")
    val postDate = prop[DateTime]("postDate")
  }

  object keys {
    val uri = key(props.uri)
  }

  object indexes {
    val postDate = index(props.blog, props.postDate)
  }

  object queries {
    import com.github.nscala_time.time.Implicits._
    import BlogPost.queryDsl._
    import BlogPost.props._
    def recentPosts(blogUri: BlogUri): Query[BlogPost] =
      blog eqs blogUri and postDate gt DateTime.now - 1.week
  }

}
