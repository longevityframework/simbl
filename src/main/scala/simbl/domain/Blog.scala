package simbl.domain

import longevity.subdomain.Persistent
import longevity.subdomain.PType

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
