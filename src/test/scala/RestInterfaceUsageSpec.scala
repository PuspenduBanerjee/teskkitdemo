import akka.actor.ActorSystem
import com.me.finalization.RestInterface
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}
import spray.testkit.ScalatestRouteTest

/**
  * Created by puspendu on 8/3/16.
  */
class RestInterfaceUsageSpec extends WordSpec with Matchers
  with ScalatestRouteTest with RestInterface
  with BeforeAndAfterAll {
  override def afterAll {
    actorRefFactory.terminate()
  }

  //val restInterfaceRef=system.actorOf(Props(new RestService(8080)),"restinterfaceRef")

  def actorRefFactory = ActorSystem(
    "RestInterfaceUsageSpec",
    ConfigFactory.parseString(RestInterfaceUsageSpec.config))

  "A RestInterface" should {
    "Respond with Conveyor Belt Id where the package should be routed to" in {
      val jctId = 20
      val containerId = 1213
      Get(s"/junctions/$jctId/decisionForContainer/$containerId") ~> route ~> check {
        val response: String = responseAs[String]
        response should include(s"CVR_$jctId")
      }
    }
    "Respond with unhandled for non-existent path" in {
      val jctId = 20
      val containerId = 1213
      Get(s"/junctions/NONEXISTENT/decisionForContainer/$containerId") ~> route ~> check {
        handled should be(false)
      }
    }
  }
}


object RestInterfaceUsageSpec {
  // Define your test specific configuration here
  val config =
  """
    akka {
      loglevel = "WARNING"
      stdout-loglevel = "WARNING"
    }
  """

}