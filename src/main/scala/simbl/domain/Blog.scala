package simbl.domain

import longevity.model.annotations.persistent

@persistent[SimblDomainModel]
case class Blog(
  uri: BlogUri,
  title: String,
  description: Markdown,
  authors: Set[Username])

object Blog {
  implicit val uriKey = primaryKey(props.uri)
}
