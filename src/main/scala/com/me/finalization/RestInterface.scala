package com.me.finalization

import akka.actor.Actor
import akka.io.IO
import com.me.finalization.Domain.{Container, Junction}
import com.me.finalization.Messages.Go
import spray.can.Http
import spray.httpx.SprayJsonSupport._
import spray.routing.{HttpServiceBase, Route}

/**
  * Created by puspendu on 8/1/16.
  */
class RestService(exposedPort: Int) extends Actor with RestInterface {

  implicit val system = context.system

  def receive = runRoute(route)
  IO(Http) ! Http.Bind(self, interface = "0.0.0.0", port = exposedPort)
}

trait RestInterface extends HttpServiceBase {
  val route: Route = {
    path("junctions" / IntNumber / "decisionForContainer" / IntNumber) { (junctionId, containerId) =>
      get {
        complete {
          println(s"Request for junction $junctionId and container $containerId")
          val junction = Junction(junctionId)
          val container = Container(containerId)
          val decision = Decisions.whereShouldContainerGo(junction, container)
          Go(decision)
        }
      }
    }
  }
}
