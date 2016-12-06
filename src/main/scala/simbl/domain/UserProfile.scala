package simbl.domain

import longevity.subdomain.annotations.component

@component
case class UserProfile(
  tagline: String,
  imageUri: Uri,
  description: Markdown)
