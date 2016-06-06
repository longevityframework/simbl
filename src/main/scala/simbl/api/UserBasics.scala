package simbl.api

import simbl.domain.Email
import simbl.domain.User

case class UserBasics(
  email: String,
  username: String,
  fullname: String) {

  def toUser = User(Email(email), username, fullname, None)

}

object UserBasics {

  def apply(user: User): UserBasics =
    UserBasics(user.email.email, user.username, user.fullname)

}
