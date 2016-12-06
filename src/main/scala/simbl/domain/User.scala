package simbl.domain

import longevity.subdomain.annotations.persistent

@persistent(keySet = Set(
  partitionKey(props.username),
  key(props.email)))
case class User(
  username: Username,
  email: Email,
  fullname: String,
  profile: Option[UserProfile]) {

  def updateProfile(profile: UserProfile): User = copy(profile = Some(profile))

  def deleteProfile: User = copy(profile = None)

}
