package simbl.domain

import longevity.model.annotations.component

@component
case class UserProfile(
  tagline: String,
  imageUri: Uri,
  description: Markdown)
