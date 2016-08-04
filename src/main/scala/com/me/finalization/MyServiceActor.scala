package com.me.finalization


import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging}
import akka.pattern.ask
import akka.util.Timeout
import spray.http.MediaTypes._
import spray.httpx.SprayJsonSupport._
import spray.httpx.encoding.Deflate
import spray.routing._
import com.me.finalization.ExtendedJSONProtocol._

import scala.concurrent.Await
import scala.concurrent.duration._



// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService with ActorLogging{

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {
  val myRoute =
      pathPrefix("finalize" / "buId" / IntNumber / "finalizationDate" / Segment ) {(buId,fDate) =>
          pathEnd {
            val finalizationRequest= FinalizationRequest(buId,fDate)
            encodeResponse(Deflate) {
              respondWithMediaType(`application/json`) {
                complete {
                  val finalizationServiceSelection=
                    actorRefFactory.actorSelection("/user/finalization-service")
                  implicit val timeout:Timeout=Timeout(100,TimeUnit.SECONDS)
                  val resultFuture = finalizationServiceSelection ? finalizationRequest
                  val result = Await.result(resultFuture,100.second).asInstanceOf[FinalizationResponse]
                  result
                }
              }
            }
          }
      }
}

