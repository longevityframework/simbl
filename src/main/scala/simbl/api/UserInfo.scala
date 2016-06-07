package simbl.api

import simbl.domain.Email
import simbl.domain.User

case class UserInfo(
  email: String,
  username: String,
  fullname: String) {

  def toUser = User(Email(email), username, fullname, None)

  def mapUser(user: User) = user.copy(
    email = Email(email),
    username = username,
    fullname = fullname)

}

object UserInfo {

  def apply(user: User): UserInfo =
    UserInfo(user.email.email, user.username, user.fullname)

}
