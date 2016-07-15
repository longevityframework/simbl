package simbl.domain

import longevity.subdomain.embeddable.Entity
import longevity.subdomain.embeddable.EntityType

case class UserProfile(
  tagline: String,
  imageUri: Uri,
  description: Markdown)
extends Entity

object UserProfile extends EntityType[UserProfile]
