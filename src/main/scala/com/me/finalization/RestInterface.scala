package com.me.finalization

import akka.actor.{Actor, ActorRef}
import akka.io.IO
import akka.pattern.ask
import com.me.finalization.Domain.{Container, Junction}
import com.me.finalization.Messages.{Go, WhereShouldIGo}
import spray.can.Http
import spray.routing.{HttpServiceBase, Route}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
  * Created by puspendu on 8/1/16.
  */
class RestService(nextActorRef: ActorRef, exposedPort: Int) extends Actor with RestInterface {

  implicit val system = context.system

  override def decider: ActorRef = nextActorRef

  def receive = runRoute(route)
  IO(Http) ! Http.Bind(self, interface = "0.0.0.0", port = exposedPort)
}

trait RestInterface extends HttpServiceBase {

  import spray.httpx.SprayJsonSupport._

  val route: Route = {
    path("junctions" / IntNumber / "decisionForContainer" / IntNumber) { (junctionId, containerId) =>
      get {
        complete {
          //println(s"Request for junction $junctionId and container $containerId")
          val junction = Junction(junctionId)
          val container = Container(containerId)
          //val decision = Decisions.whereShouldContainerGo(junction, container)
          decider.ask(WhereShouldIGo(junction, container))(5 seconds).mapTo[Go]
        }
      }
    }
  }

  def decider: ActorRef = ???
}
