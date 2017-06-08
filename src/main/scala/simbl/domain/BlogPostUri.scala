package simbl.domain

import longevity.model.annotations.keyVal

@keyVal[SimblDomainModel, BlogPost]
case class BlogPostUri(uri: Uri)
