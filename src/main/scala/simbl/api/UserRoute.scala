package simbl.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import org.json4s.DefaultFormats
import org.json4s.native.Serialization

class UserRoute(
  private val userApi: UserApi) {

  implicit val serialization = Serialization
  implicit val formats = DefaultFormats

  val route: Route =
    rejectEmptyResponse {
      pathPrefix("users") {
        pathEndOrSingleSlash {
          post {
            entity(as[UserInfo]) {
              info => complete(userApi.createUser(info))
            }
          }
        } ~
        pathPrefix(Segment) { username =>
          pathEndOrSingleSlash {
            get {
              complete(userApi.retrieveUser(username))
            } ~
            put {
              entity(as[UserInfo]) { info =>
                complete(userApi.updateUser(username, info))
              }
            } ~
            delete {
              complete(userApi.deleteUser(username))
            }
          } ~
          path("profile") {
            get {
              complete(userApi.retrieveProfile(username))
            } ~
            put {
              entity(as[ProfileInfo]) { info =>
                complete(userApi.updateProfile(username, info))
              }
            } ~
            delete {
              complete(userApi.deleteProfile(username))
            }
          }
        }
      }
    }


}
