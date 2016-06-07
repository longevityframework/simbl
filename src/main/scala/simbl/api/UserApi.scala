package simbl.api

import longevity.persistence.PState
import longevity.persistence.Repo
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import simbl.domain.User

class UserApi(
  private val userRepo: Repo[User])(
  implicit context: ExecutionContext){

  // TODO: handle duplicate key
  def createUser(basics: UserBasics): Future[UserBasics] = {
    userRepo.create(basics.toUser).map(_.get).map(UserBasics(_))
  }
 
  def retrieveUser(username: String): Future[Option[UserBasics]] = {
    val keyVal = User.keys.username(username)
    def stateToBasics(state: PState[User]) = UserBasics(state.get)
    def stateOptToBasicsOpt(stateOpt: Option[PState[User]]) = stateOpt.map(stateToBasics)
    userRepo.retrieve(keyVal).map(stateOptToBasicsOpt)
  }

}
