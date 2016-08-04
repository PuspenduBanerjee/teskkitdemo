package com.me.finalization

import com.me.finalization.Domain.{Container, Junction}
import spray.json.DefaultJsonProtocol._

/**
  * Created by puspendu on 8/1/16.
  */
object Messages {

  case class WhereShouldIGo(junction: Junction, container: Container)

  case class Go(targetConveyor: String)

  object Go {
    implicit val goJson = jsonFormat1(Go.apply)
  }

}