package simbl.domain

import longevity.model.annotations.persistent

@persistent(keySet = Set(
  primaryKey(props.username),
  key(props.email)))
case class User(
  username: Username,
  email: Email,
  fullname: String,
  profile: Option[UserProfile]) {

  def updateProfile(profile: UserProfile): User = copy(profile = Some(profile))

  def deleteProfile: User = copy(profile = None)

}
