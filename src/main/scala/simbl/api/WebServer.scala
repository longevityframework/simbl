package simbl.api

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import scala.concurrent.Future
import spray.json.DefaultJsonProtocol._
 
object WebServer {
 
  // domain model
  final case class Item(name: String, id: Long)
  final case class Order(items: List[Item])
 
  // formats for unmarshalling and marshalling
  implicit val itemFormat = jsonFormat2(Item)
  implicit val orderFormat = jsonFormat1(Order)
 
  // (fake) async database query api
  def fetchItem(itemId: Long): Future[Option[Item]] = Future.successful(Some(Item("itemName", itemId)))
  def saveOrder(order: Order): Future[Done] = Future.successful(Done)
 
  def main(args: Array[String]) {
 
    // needed to run the route
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    println("hi from Webserver " + new java.util.Date())

    val route: Route =
      get {
        pathPrefix("item" / LongNumber) { id =>
          // there might be no item for a given id
          val maybeItem: Future[Option[Item]] = fetchItem(id)
 
          onSuccess(maybeItem) {
            case Some(item) => complete(item)
            case None       => complete(StatusCodes.NotFound)
          }
        }
      } ~ post {
        path("create-order") {
          entity(as[Order]) {
            order =>
            val saved: Future[Done] = saveOrder(order)
            onComplete(saved) { done =>
              complete("order created")
            }
          }
        }
      }

    implicit val executor = system.dispatcher

    val host = system.settings.config.getString("http.host")
    val port = system.settings.config.getInt("http.port")
    Http().bindAndHandle(route, host, port)
 
  }
}
