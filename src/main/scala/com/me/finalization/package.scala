package com.me

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import spray.json.DefaultJsonProtocol

/**
  * Created by puspendu on 7/26/16.
  */
package object finalization {
  def ISOFormattedCurrentZonedDateTime = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT)


  case class FinalizationRequest(buId: Int, finalizationDate: String)

  case class FinalizationResponse(buId: Int, finalizationDate: String, processDate: String = ISOFormattedCurrentZonedDateTime)

  object Domain {

    case class Junction(id: Int)

    case class Container(id: Int)

  }


  object ExtendedJSONProtocol extends DefaultJsonProtocol {
    implicit val defaultFinalizationRequestFormat = jsonFormat2(FinalizationRequest)
    implicit val defaultFinalizationResponseFormat = jsonFormat3(FinalizationResponse)
  }

}
