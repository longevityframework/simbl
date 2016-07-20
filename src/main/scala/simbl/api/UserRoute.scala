package simbl.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import org.json4s.DefaultFormats
import org.json4s.native.Serialization
import scala.util.Failure
import scala.util.Success
import simbl.service.DuplicateEmailException
import simbl.service.DuplicateUsernameException
import simbl.service.UserService

/** defines the Akka HTTP routes for user endpoints */
class UserRoute(
  private val userService: UserService) {

  implicit val serialization = Serialization
  implicit val formats = DefaultFormats

  val route: Route =
    rejectEmptyResponse {
      pathPrefix("users") {
        pathEndOrSingleSlash {
          post {
            entity(as[UserInfo]) {
              info => onComplete(userService.createUser(info)) {
                case Success(info) =>
                  complete(info)
                case Failure(e: DuplicateUsernameException) =>
                  complete(StatusCodes.Conflict -> e.getMessage)
                case Failure(e: DuplicateEmailException) =>
                  complete(StatusCodes.Conflict -> e.getMessage)
                case Failure(e) =>
                  failWith(e)
              }
            }
          } ~
          get {
            complete(userService.retrieveAllUsers())
          }
        } ~
        pathPrefix(Segment) { username =>
          pathEndOrSingleSlash {
            get {
              complete(userService.retrieveUser(username))
            } ~
            put {
              entity(as[UserInfo]) { info =>
                onComplete(userService.updateUser(username, info)) {
                  case Success(info) =>
                    complete(info)
                  case Failure(e: DuplicateUsernameException) =>
                    complete(StatusCodes.Conflict -> e.getMessage)
                  case Failure(e: DuplicateEmailException) =>
                    complete(StatusCodes.Conflict -> e.getMessage)
                  case Failure(e) =>
                    failWith(e)
                }
              }
            } ~
            delete {
              complete(userService.deleteUser(username))
            }
          } ~
          path("profile") {
            get {
              complete(userService.retrieveProfile(username))
            } ~
            put {
              entity(as[ProfileInfo]) { info =>
                complete(userService.updateProfile(username, info))
              }
            } ~
            delete {
              complete(userService.deleteProfile(username))
            }
          }
        }
      }
    }

}
