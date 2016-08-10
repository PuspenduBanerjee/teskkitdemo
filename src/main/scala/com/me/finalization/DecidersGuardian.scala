package com.me.finalization

import akka.actor.{Actor, Props}
import com.me.finalization.Messages.WhereShouldIGo

/**
  * Created by puspendu on 8/5/16.
  */
class DecidersGuardian extends Actor {
  def receive = {
    case m: WhereShouldIGo =>
      val name = s"J${m.junction.id}"
      val actor = context.child(name) getOrElse {
        println(s"Creating Actor: $name")
        context.actorOf(Props[SortingDecider], name)
      }
      println(s"Forwarding ${m.junction} to Actor: $name")
      actor forward m
  }
}