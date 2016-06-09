package simbl.api

import longevity.persistence.PState
import longevity.persistence.Repo
import longevity.subdomain.ptype.KeyVal
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import simbl.domain.User

/** service methods that back the [[UserRoute user routes]] */
class UserService(
  private val userRepo: Repo[User])(
  implicit context: ExecutionContext) {

  /** creates and persists a new [[User]] from the [[UserInfo]], returning a
   * `UserInfo` reflecting the persisted `User`.
   */
  def createUser(info: UserInfo): Future[UserInfo] = {
    // TODO: create local exception types DupEmail & DupUsername
    // TODO: wrap this in a recover. catch DuplicateKeyValE, translate into local exception
    // TODO: in the route, handle the local exceptions
    for {
      created <- userRepo.create(info.toUser)
    } yield {
      UserInfo(created.get)
    }
  }

  /** retrieves a [[User]] by username, returning a [[UserInfo]] reflecting the
   * persisted `User`. returns `None` if no such user by that username.
   */
  def retrieveUser(username: String): Future[Option[UserInfo]] = {
    for {
      retrieved <- userRepo.retrieve(keyVal(username))
    } yield {
      def stateToInfo(state: PState[User]) = UserInfo(state.get)
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
        Some(UserInfo(updated.get))
      }
    } recover {
      case e: NoSuchElementException => None
    }
  }

  /** deletes a [[User]] by username, returning a [[UserInfo]] reflecting the
   * deleted `User`. returns `None` if no such user by that username.
   */
  def deleteUser(username: String): Future[Option[UserInfo]] = {
    {
      for {
        retrieved <- userRepo.retrieveOne(keyVal(username))
        deleted <- userRepo.delete(retrieved)
      } yield {
        Some(UserInfo(deleted.get))
      }
    } recover {
      case e: NoSuchElementException => None
    }
  }

  /** retrieves a user profile by username, returning a [[ProfileInfo]]
   * reflecting the persisted `User`. returns `None` if no such user by that
   * username.
   */
  def retrieveProfile(username: String): Future[Option[ProfileInfo]] = {
    for {
      retrieved <- userRepo.retrieve(keyVal(username))
    } yield {
      def stateToInfo(state: PState[User]) = state.get.profile.map(ProfileInfo(_))
      retrieved.flatMap(stateToInfo)
    }
  }

  /** updates a [[UserProfile user profile]] by username, returning a
   * [[ProfileInfo]] reflecting the persisted `User`. returns `None` if no such
   * user by that username.
   */
  def updateProfile(username: String, profile: ProfileInfo): Future[Option[ProfileInfo]] = {
    {
      for {
        retrieved <- userRepo.retrieveOne(keyVal(username))
        modified = retrieved.map(_.copy(profile = Some(profile.toProfile))) // TODO clean this up
        updated <- userRepo.update(modified)
      } yield {
        updated.get.profile.map(ProfileInfo(_))
      }
    } recover {
      case e: NoSuchElementException => None
    }
  }

  /** deletes a [[UserProfile user profile]] by username, returning a
   * [[ProfileInfo]] reflecting the deleted profile. returns `None` if no such
   * user by that username, or if the user did not have a profile to delete
   */
  def deleteProfile(username: String): Future[Option[ProfileInfo]] = {
    {
      for {
        retrieved <- userRepo.retrieveOne(keyVal(username))
        modified = retrieved.map(_.copy(profile = None)) // TODO clean this up (UserService)
        updated <- userRepo.update(modified)
      } yield {
        retrieved.get.profile.map(ProfileInfo(_))
      }
    } recover {
      case e: NoSuchElementException => None
    }
  }

  /** produces a key value for looking up a user by username */
  private def keyVal(username: String): KeyVal[User] = User.keys.username(username)

}
