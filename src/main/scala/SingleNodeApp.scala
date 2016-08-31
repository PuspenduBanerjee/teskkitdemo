import akka.actor.{ActorSystem, Props}
import akka.camel.CamelExtension
import com.me.finalization._

/**
  * Created by puspendu on 8/1/16.
  */
object SingleNodeApp extends App {
  implicit val system = ActorSystem("sorter")
//  val deciderGuardian = system.actorOf(Props[DecidersGuardian])
//  system.actorOf(Props(classOf[RestService], deciderGuardian, 18080), "restservice")
//  system.actorOf(Props[CamelRestInterface])
val camel = CamelExtension(system)
  val camelCXFRest = system.actorOf(Props[CamelCXFRestInterface], name = "CamelCXFRestInterface")
  camel.context.addRoutes(new CustomRouteBuilder(system, camelCXFRest))
}