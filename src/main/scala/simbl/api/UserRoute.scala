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
    pathPrefix("users") {
      post {
        entity(as[UserInfo]) {
          info => complete(userApi.createUser(info))
        }
      } ~
      // TODO rm redunancies below
      get {
        path(Segment) { username =>
          rejectEmptyResponse {
            complete(userApi.retrieveUser(username))
          }
        }
      } ~
      put {
        path(Segment) { username =>
          rejectEmptyResponse {
            entity(as[UserInfo]) { info =>
              complete(userApi.updateUser(username, info))
            }
          }
        }
      } ~
      delete {
        path(Segment) { username =>
          rejectEmptyResponse {
            complete(userApi.deleteUser(username))
          }
        }
      }
    }

}
