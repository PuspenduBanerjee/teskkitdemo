import akka.actor.ActorSystem
import akka.testkit.{DefaultTimeout, ImplicitSender, TestActors, TestKit}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import scala.concurrent.duration._
/**
  * Created by puspendu on 8/3/16.
  */
class EchoUsageSpec extends TestKit(ActorSystem(
  "EchoUsageSpec",
  ConfigFactory.parseString(EchoUsageSpec.config))) with DefaultTimeout with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll{


  val echoRef=system.actorOf(TestActors.echoActorProps)

  override def afterAll {
    shutdown()
  }

  "An EchoActor" should {
    "Respond with the same message it receives" in {
      within(50 millis) {
        echoRef ! "test"
        expectMsg("test")
      }
    }
  }
}


object EchoUsageSpec {
  // Define your test specific configuration here
  val config =
  """
    akka {
      loglevel = "WARNING"
    }
  """

}
