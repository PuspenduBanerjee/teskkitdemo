package com.me.finalization

/**
  * Created by puspendu on 8/22/16.
  */



import akka.actor.{Actor, ActorRef, ActorSystem}
import akka.camel.{CamelExtension, CamelMessage, Consumer}
import org.apache.camel.builder.RouteBuilder
import akka.camel._

case class Request(body: java.util.List[String], headers: java.util.Map[String, Object])

class CamelCXFRestInterface extends Actor {
  //val camel = CamelExtension(context.system)
  //def endpointUri = "cxfrs:http://localhost:18878/camel/default"

  def receive =  {
    case msg: CamelMessage =>
      sender() ! "Hello"
  }
}

class CustomRouteBuilder(system: ActorSystem, responder: ActorRef)
  extends RouteBuilder {
  def configure {
    from("cxfrs:http://localhost:18878/camel/default?resourceClasses=com.me.finalization.rest.FinalizationResource").to(responder)
  }
}

