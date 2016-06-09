package simbl.service

import scala.concurrent.Future
import simbl.api.ProfileInfo
import simbl.api.UserInfo

/** service methods to back the [[UserRoute user routes]] */
trait UserService {

  /** creates and persists a new [[User]] from the [[UserInfo]], returning a
   * `UserInfo` reflecting the persisted `User`.
   */
  def createUser(info: UserInfo): Future[UserInfo]

  /** retrieves a [[User]] by username, returning a [[UserInfo]] reflecting the
   * persisted `User`. returns `None` if no such user by that username.
   */
  def retrieveUser(username: String): Future[Option[UserInfo]]

  /** updates a [[User]] by username, returning a [[UserInfo]] reflecting the
   * persisted `User`. returns `None` if no such user by that username.
   */
  def updateUser(username: String, info: UserInfo): Future[Option[UserInfo]]

  /** deletes a [[User]] by username, returning a [[UserInfo]] reflecting the
   * deleted `User`. returns `None` if no such user by that username.
   */
  def deleteUser(username: String): Future[Option[UserInfo]]

  /** retrieves a user profile by username, returning a [[ProfileInfo]]
   * reflecting the persisted `User`. returns `None` if no such user by that
   * username.
   */
  def retrieveProfile(username: String): Future[Option[ProfileInfo]]

  /** updates a [[UserProfile user profile]] by username, returning a
   * [[ProfileInfo]] reflecting the persisted `User`. returns `None` if no such
   * user by that username.
   */
  def updateProfile(username: String, profile: ProfileInfo): Future[Option[ProfileInfo]]

  /** deletes a [[UserProfile user profile]] by username, returning a
   * [[ProfileInfo]] reflecting the deleted profile. returns `None` if no such
   * user by that username, or if the user did not have a profile to delete
   */
  def deleteProfile(username: String): Future[Option[ProfileInfo]]

}
