package simbl.domain

import longevity.subdomain.embeddable.Embeddable

case class UserProfile(
  tagline: String,
  imageUri: Uri,
  description: Markdown)
extends Embeddable
