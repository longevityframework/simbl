package simbl.domain

import longevity.subdomain.annotations.persistent

@persistent(keySet = Set(partitionKey(props.uri)))
case class Blog(
  uri: BlogUri,
  title: String,
  description: Markdown,
  authors: Set[Username])
