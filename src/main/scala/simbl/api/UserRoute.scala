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
      get {
        rejectEmptyResponse {
          path(Segment) {
            username => complete(userApi.retrieveUser(username))
          }
        }
      } ~
      put {
        rejectEmptyResponse {
          path(Segment) {
            username =>
            entity(as[UserInfo]) {
              info =>
              complete(userApi.updateUser(username, info))
            }
          }
        }
      }
    }

}
