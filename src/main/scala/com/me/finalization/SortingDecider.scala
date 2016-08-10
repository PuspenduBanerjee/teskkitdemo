package com.me.finalization

import akka.actor.{Actor, ActorLogging}
import com.me.finalization.Messages.{Go, WhereShouldIGo}

/**
  * Created by puspendu on 8/5/16.
  */

class SortingDecider extends Actor with ActorLogging {
  def receive: Receive = {
    case WhereShouldIGo(junction, container) => {
      val targetConveyor = Decisions.whereShouldContainerGo(junction, container)
      sender ! Go(targetConveyor)
    }
  }
}