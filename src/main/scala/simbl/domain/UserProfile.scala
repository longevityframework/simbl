package simbl.domain

import longevity.model.annotations.component

@component[SimblDomainModel]
case class UserProfile(
  tagline: String,
  imageUri: Uri,
  description: Markdown)
