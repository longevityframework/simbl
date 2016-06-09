package simbl.domain

import longevity.subdomain.persistent.Root
import longevity.subdomain.ptype.RootType

case class User(
  email: Email,
  username: String,
  fullname: String,
  profile: Option[UserProfile])
extends Root {

  def updateProfile(profile: UserProfile): User = copy(profile = Some(profile))

  def deleteProfile: User = copy(profile = None)

}

object User extends RootType[User] {
  object props {
    val username = prop[String]("username")
    val email = prop[Email]("email")
  }
  object keys {
    val username = key(props.username)
    val email = key(props.email)
  }
  object indexes {
  }
}
