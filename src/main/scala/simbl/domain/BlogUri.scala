package simbl.domain

import longevity.model.annotations.keyVal

@keyVal[SimblDomainModel, Blog]
case class BlogUri(uri: Uri)
