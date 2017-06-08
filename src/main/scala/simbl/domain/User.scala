package simbl.domain

import longevity.model.annotations.persistent

@persistent[SimblDomainModel]
case class User(
  username: Username,
  email: Email,
  fullname: String,
  profile: Option[UserProfile]) {

  def updateProfile(profile: UserProfile): User = copy(profile = Some(profile))

  def deleteProfile: User = copy(profile = None)

}

object User {
  implicit val usernameKey = primaryKey(props.username)
  implicit val emailKey = key(props.email)
}
