package simbl.domain

import longevity.subdomain.Assoc
import longevity.subdomain.persistent.Root
import longevity.subdomain.ptype.RootType

case class Blog(
  uri: Uri,
  title: String,
  description: Markdown,
  authors: Set[Assoc[User]])
extends Root

object Blog extends RootType[Blog] {
  object props {
    val uri = prop[Uri]("uri")
  }
  object keys {
    val uri = key(props.uri)
  }
  object indexes {
  }
}
