package simbl.api

import simbl.domain.Email
import simbl.domain.User
import simbl.domain.Username

case class UserInfo(
  username: String,
  email: String,
  fullname: String) {

  def toUser = User(Username(username), Email(email), fullname, None)

  /** updates a [[User]] according to the information in this [[UserInfo]] */
  def mapUser(user: User) = user.copy(
    username = Username(username),
    email = Email(email),
    fullname = fullname)

}

object UserInfo {

  def apply(user: User): UserInfo =
    UserInfo(user.username.username, user.email.email, user.fullname)

}
