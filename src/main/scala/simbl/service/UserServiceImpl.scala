package simbl.service

import longevity.exceptions.persistence.DuplicateKeyValException
import longevity.persistence.PState
import longevity.persistence.Repo
import longevity.subdomain.ptype.KeyVal
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import simbl.api.ProfileInfo
import simbl.api.UserInfo
import simbl.domain.User

/** default implementation of service to back the [[UserRoute user routes]] */
class UserServiceImpl(
  private val userRepo: Repo[User])(
  implicit context: ExecutionContext)
extends UserService {

  def createUser(info: UserInfo): Future[UserInfo] = {
    {
      for {
        created <- userRepo.create(info.toUser)
      } yield {
        UserInfo(created.get)
      }
    } recover {
      case e: DuplicateKeyValException[_] => e.key match {
        case User.keys.username =>
          throw new DuplicateUsernameException(info.username)
        case User.keys.email =>
          throw new DuplicateEmailException(info.email)
      }
    }
  }
    
  def retrieveUser(username: String): Future[Option[UserInfo]] = {
    for {
      retrieved <- userRepo.retrieve(keyVal(username))
    } yield {
      def stateToInfo(state: PState[User]) = UserInfo(state.get)
      retrieved.map(stateToInfo)
    }
  }

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

  def retrieveProfile(username: String): Future[Option[ProfileInfo]] = {
    for {
      retrieved <- userRepo.retrieve(keyVal(username))
    } yield {
      def stateToInfo(state: PState[User]) = state.get.profile.map(ProfileInfo(_))
      retrieved.flatMap(stateToInfo)
    }
  }

  def updateProfile(username: String, profile: ProfileInfo): Future[Option[ProfileInfo]] = {
    {
      for {
        retrieved <- userRepo.retrieveOne(keyVal(username))
        modified = retrieved.map(_.updateProfile(profile.toProfile))
        updated <- userRepo.update(modified)
      } yield {
        updated.get.profile.map(ProfileInfo(_))
      }
    } recover {
      case e: NoSuchElementException => None
    }
  }

  def deleteProfile(username: String): Future[Option[ProfileInfo]] = {
    {
      for {
        retrieved <- userRepo.retrieveOne(keyVal(username))
        modified = retrieved.map(_.deleteProfile)
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
