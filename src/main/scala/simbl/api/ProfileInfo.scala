package simbl.api

import simbl.domain.Markdown
import simbl.domain.Uri
import simbl.domain.UserProfile

case class ProfileInfo(
  tagline: String,
  imageUri: String,
  description: String) {

  def toProfile = UserProfile(tagline, Uri(imageUri), Markdown(description))

}

object ProfileInfo {

  def apply(profile: UserProfile): ProfileInfo =
    ProfileInfo(profile.tagline, profile.imageUri.uri, profile.description.markdown)

}
