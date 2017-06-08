package simbl.service

import longevity.exceptions.persistence.DuplicateKeyValException
import longevity.persistence.PState
import longevity.persistence.Repo
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import simbl.api.ProfileInfo
import simbl.api.UserInfo
import simbl.domain.SimblDomainModel
import simbl.domain.User
import simbl.domain.Username

/** default implementation of service to back the [[UserRoute user routes]] */
class UserServiceImpl(
  private val repo: Repo[SimblDomainModel])(
  implicit context: ExecutionContext)
extends UserService {

  def createUser(info: UserInfo): Future[UserInfo] = {
    {
      for {
        created <- repo.create(info.toUser)
      } yield {
        UserInfo(created.get)
      }
    } recover {
      case e: DuplicateKeyValException[_, _] => handleDuplicateKeyVal(e, info)
    }
  }

  def retrieveAllUsers(): Future[Seq[UserInfo]] = {
    def stateToInfo(state: PState[User]) = UserInfo(state.get)
    def usersToUserInfos(users: Seq[PState[User]]) = users.map(stateToInfo)
    import User.queryDsl._
    repo.queryToFutureVec(filterAll).map(usersToUserInfos)
  }

  def retrieveUser(username: String): Future[Option[UserInfo]] = {
    for {
      retrieved <- repo.retrieve[User](Username(username))
    } yield {
      def stateToInfo(state: PState[User]) = UserInfo(state.get)
      retrieved.map(stateToInfo)
    }
  }

  def updateUser(username: String, info: UserInfo): Future[Option[UserInfo]] = {
    {
      for {
        retrieved <- repo.retrieveOne[User](Username(username))
        modified = retrieved.map(info.mapUser)
        updated <- repo.update(modified)
      } yield {
        Some(UserInfo(updated.get))
      }
    } recover {
      case e: DuplicateKeyValException[_, _] => handleDuplicateKeyVal(e, info)
      case e: NoSuchElementException => None
    }
  }

  def deleteUser(username: String): Future[Option[UserInfo]] = {
    {
      for {
        retrieved <- repo.retrieveOne[User](Username(username))
        deleted <- repo.delete(retrieved)
      } yield {
        Some(UserInfo(deleted.get))
      }
    } recover {
      case e: NoSuchElementException => None
    }
  }

  def retrieveProfile(username: String): Future[Option[ProfileInfo]] = {
    for {
      retrieved <- repo.retrieve[User](Username(username))
    } yield {
      def stateToInfo(state: PState[User]) = state.get.profile.map(ProfileInfo(_))
      retrieved.flatMap(stateToInfo)
    }
  }

  def updateProfile(username: String, profile: ProfileInfo): Future[Option[ProfileInfo]] = {
    {
      for {
        retrieved <- repo.retrieveOne[User](Username(username))
        modified = retrieved.map(_.updateProfile(profile.toProfile))
        updated <- repo.update(modified)
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
        retrieved <- repo.retrieveOne[User](Username(username))
        modified = retrieved.map(_.deleteProfile)
        updated <- repo.update(modified)
      } yield {
        retrieved.get.profile.map(ProfileInfo(_))
      }
    } recover {
      case e: NoSuchElementException => None
    }
  }

  /** converts longevity duplicate key val exception into simbl exception */
  private def handleDuplicateKeyVal(e: DuplicateKeyValException[_, _], info: UserInfo): Nothing = {
    e.key.prop match {
      case User.props.username =>
        throw new DuplicateUsernameException(info.username)
      case User.props.email =>
        throw new DuplicateEmailException(info.email)
    }
  }

}
