package simbl.domain

import longevity.subdomain.Persistent
import longevity.subdomain.PType

case class User(
  username: Username,
  email: Email,
  fullname: String,
  profile: Option[UserProfile])
extends Persistent {

  def updateProfile(profile: UserProfile): User = copy(profile = Some(profile))

  def deleteProfile: User = copy(profile = None)

}

object User extends PType[User] {
  object props {
    val username = prop[Username]("username")
    val email = prop[Email]("email")
  }
  object keys {
    val username = key(props.username)
    val email = key(props.email)
  }
  object indexes {
  }
}
