package simbl.domain

import longevity.subdomain.embeddable.Entity

case class UserProfile(
  tagline: String,
  imageUri: Uri,
  description: Markdown)
extends Entity
