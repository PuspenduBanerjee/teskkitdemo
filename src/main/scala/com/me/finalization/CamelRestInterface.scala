package com.me.finalization

/**
  * Created by puspendu on 8/22/16.
  */

import akka.camel.{CamelMessage, Consumer}

class CamelRestInterface extends Consumer {
  def endpointUri = "jetty:http://localhost:18877/camel/default"

  def receive = {
    case msg: CamelMessage => sender() ! ("Hello %s" format msg.headerAs[String]("from").get)
  }
}

