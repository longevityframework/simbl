package simbl.domain

import longevity.subdomain.Assoc
import longevity.subdomain.persistent.Root
import longevity.subdomain.ptype.RootType
import org.joda.time.DateTime

case class BlogPost(
  uriPathSuffix: String,
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
    val blog = prop[Assoc[Blog]]("blog")
    val uriPathSuffix = prop[String]("uriPathSuffix")
    val postDate = prop[DateTime]("postDate")
  }
  object keys {
    val uri = key(props.blog, props.uriPathSuffix)
  }
  object indexes {
    val postDate = index(props.blog, props.postDate)
  }
}
