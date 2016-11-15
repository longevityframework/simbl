package simbl.domain

import longevity.subdomain.PType

case class Blog(
  uri: BlogUri,
  title: String,
  description: Markdown,
  authors: Set[Username])

object Blog extends PType[Blog] {
  object props {
    val uri = prop[BlogUri]("uri")
  }
  object keys {
    val uri = partitionKey(props.uri)
  }
}
