import akka.actor.{ActorSystem, Props}
import com.me.finalization.{CamelRestInterface, DecidersGuardian, RestService}

/**
  * Created by puspendu on 8/1/16.
  */
object SingleNodeApp extends App {
  implicit val system = ActorSystem("sorter")
  val deciderGuardian = system.actorOf(Props[DecidersGuardian])
  system.actorOf(Props(classOf[RestService], deciderGuardian, 18080), "restservice")
  system.actorOf(Props[CamelRestInterface])
}