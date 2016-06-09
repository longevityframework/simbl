package simbl.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import org.json4s.DefaultFormats
import org.json4s.native.Serialization
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
              info => complete(userService.createUser(info))
            }
          }
        } ~
        pathPrefix(Segment) { username =>
          pathEndOrSingleSlash {
            get {
              complete(userService.retrieveUser(username))
            } ~
            put {
              entity(as[UserInfo]) { info =>
                complete(userService.updateUser(username, info))
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
