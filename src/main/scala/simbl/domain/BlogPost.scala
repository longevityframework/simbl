package simbl.domain

import longevity.subdomain.Assoc
import longevity.subdomain.persistent.Root
import longevity.subdomain.ptype.Query
import longevity.subdomain.ptype.RootType
import org.joda.time.DateTime

case class BlogPost(
  uri: Uri,
  title: String,
  slug: Option[Markdown] = None,
  content: Markdown,
  labels: Set[String] = Set(),
  postDate: DateTime,
  blog: Assoc[Blog],
  authors: Set[Assoc[User]])
extends Root

object BlogPost extends RootType[BlogPost] {

  object props {
    val uri = prop[Uri]("uri")
    val blog = prop[Assoc[Blog]]("blog")
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
    def recentPosts(blogAssoc: Assoc[Blog]): Query[BlogPost] =
      blog eqs blogAssoc and postDate gt DateTime.now - 1.week
  }

}
