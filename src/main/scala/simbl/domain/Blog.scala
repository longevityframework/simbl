package simbl.domain

import longevity.subdomain.persistent.Persistent
import longevity.subdomain.ptype.PType

case class Blog(
  uri: BlogUri,
  title: String,
  description: Markdown,
  authors: Set[Username])
extends Persistent

object Blog extends PType[Blog] {
  object props {
    val uri = prop[BlogUri]("uri")
  }
  object keys {
    val uri = key(props.uri)
  }
  object indexes {
  }
}
