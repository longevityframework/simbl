package simbl.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import longevity.persistence.PState
import longevity.persistence.Repo
import org.json4s.DefaultFormats
import org.json4s.native.Serialization
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import simbl.domain.User

class UserApi(
  private val userRepo: Repo[User])(
  implicit context: ExecutionContext){

  implicit val serialization = Serialization
  implicit val formats = DefaultFormats

  val route: Route =
    pathPrefix("users") {
      post {
        entity(as[UserBasics]) {
          basics => complete(createUser(basics))
        }
      } ~
      get {
        path(Segment) {
          username => complete(retrieveUser(username))
        }
      }
    }

  // TODO: handle duplicate key
  def createUser(basics: UserBasics): Future[UserBasics] = {
    userRepo.create(basics.toUser).map(_.get).map(UserBasics(_))
  }
 
  // TODO: None should cause NotFound response (right now it makes empty response)
  def retrieveUser(username: String): Future[Option[UserBasics]] = {
    val keyVal = User.keys.username(username)
    def stateToBasics(state: PState[User]) = UserBasics(state.get)
    def stateOptToBasicsOpt(stateOpt: Option[PState[User]]) = stateOpt.map(stateToBasics)
    userRepo.retrieve(keyVal).map(stateOptToBasicsOpt)
  }

}
