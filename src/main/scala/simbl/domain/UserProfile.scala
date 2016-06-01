package simbl.domain

import longevity.subdomain.entity.Entity
import longevity.subdomain.entity.EntityType

case class UserProfile(
  tagline: String,
  imageUri: Uri,
  description: Markdown)
extends Entity

object UserProfile extends EntityType[UserProfile]
