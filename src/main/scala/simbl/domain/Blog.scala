package simbl.domain

import longevity.subdomain.persistent.Root
import longevity.subdomain.ptype.RootType

case class Blog(
  uri: BlogUri,
  title: String,
  description: Markdown,
  authors: Set[Username])
extends Root

object Blog extends RootType[Blog] {
  object props {
    val uri = prop[BlogUri]("uri")
  }
  object keys {
    val uri = key(props.uri)
  }
  object indexes {
  }
}
