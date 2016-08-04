package com.me.finalization

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.duration._


/**
  * Created by puspendu on 7/25/16.
  */


// prints a greeting
class FinalizationActor extends Actor with ActorLogging {
  def receive = {
    case FinalizationRequest(buId, finalizationDate) =>
      println(s"got It! BU: $buId  FinalizationDate: $finalizationDate")
      sender ! FinalizationResponse(buId, finalizationDate)
  }
}


object FinalizationService extends App {


  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")

  // create and start our service actor
  val service = system.actorOf(Props[MyServiceActor], "demo-service")

  // create and start our greeter actor
  val greetPrinter = system.actorOf(Props[FinalizationActor], "finalization-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 18080)
}