package simbl.domain

case class UserProfile(
  tagline: String,
  imageUri: Uri,
  description: Markdown)
