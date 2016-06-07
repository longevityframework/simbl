package simbl.api

import longevity.persistence.PState
import longevity.persistence.Repo
import longevity.subdomain.ptype.KeyVal
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import simbl.domain.User

/** API service methods that back the [[UserRoute user routes]] */
class UserApi(
  private val userRepo: Repo[User])(
  implicit context: ExecutionContext){

  /** creates and persists a new [[User]] from the [[UserInfo]], returning a
   * `UserInfo` reflecting the persisted `User`.
   */
  def createUser(info: UserInfo): Future[UserInfo] = {
    for {
      created <- userRepo.create(info.toUser)
    } yield {
      stateToInfo(created)
    }
  }

  /** retrieves a [[User]] by username, returning a [[UserInfo]] reflecting the
   * persisted `User`. returns `None` if no such user by that username.
   */
  def retrieveUser(username: String): Future[Option[UserInfo]] = {
    for {
      retrieved <- userRepo.retrieve(keyVal(username))
    } yield {
      retrieved.map(stateToInfo)
    }
  }

  /** updates a [[User]] by username, returning a [[UserInfo]] reflecting the
   * persisted `User`. returns `None` if no such user by that username.
   */
  def updateUser(username: String, info: UserInfo): Future[Option[UserInfo]] = {
    {
      for {
        retrieved <- userRepo.retrieveOne(keyVal(username))
        modified = retrieved.map(info.mapUser)
        updated <- userRepo.update(modified)
      } yield {
        Some(stateToInfo(updated))
      }
    } recover {
      case e: NoSuchElementException => None
    }
  }

  // TODO
  // - DeleteUser(username: String): UserBasics
  //   - DELETE /users/username

  /** produces a key value for looking up a user by username */
  private def keyVal(username: String): KeyVal[User] = User.keys.username(username)

  /** translates from a user persistent state to a `UserInfo` */
  private def stateToInfo(state: PState[User]): UserInfo = UserInfo(state.get)

}
