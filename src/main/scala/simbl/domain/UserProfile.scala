package simbl.domain

import longevity.subdomain.Embeddable

case class UserProfile(
  tagline: String,
  imageUri: Uri,
  description: Markdown)
extends Embeddable
