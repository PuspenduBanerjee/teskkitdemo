import akka.actor.Props
import akka.testkit.TestKit
import com.me.finalization.{RestInterface, RestService}
import org.scalatest.{Matchers, WordSpec}
import spray.testkit.ScalatestRouteTest

import scala.concurrent.duration._

/**
  * Created by puspendu on 8/3/16.
  */
class RestInterfaceUsageSpec  extends  WordSpec with Matchers with ScalatestRouteTest with RestInterface{
  def actorRefFactory = system
  //val restInterfaceRef=system.actorOf(Props(new RestService(8080)),"restinterfaceRef")



  "A RestInterface" should {
    "Respond with Conveyor Belt Id where the package should be routed to" in {
       val jctId=20
      val containerId=1213
        Get(s"/junctions/$jctId/decisionForContainer/$containerId") ~> route ~> check {
          val response:String=responseAs[String]
          response.contains("CVR_"+jctId)
        }
      }
    }
  }



object RestInterfaceUsageSpec {
  // Define your test specific configuration here
  val config =
  """
    akka {
      loglevel = "DEBUG"
    }
               """

}