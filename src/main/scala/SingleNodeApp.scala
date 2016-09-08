import akka.actor.{ActorSystem, Props}
import akka.camel.CamelExtension
import com.me.finalization._
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
  * Created by puspendu on 8/1/16.
  */
object SingleNodeApp extends App {
 // implicit val system = ActorSystem("sorter")
//  val deciderGuardian = system.actorOf(Props[DecidersGuardian])
//  system.actorOf(Props(classOf[RestService], deciderGuardian, 18080), "restservice")
//  system.actorOf(Props[CamelRestInterface])
  val ctx = new AnnotationConfigApplicationContext()
  ctx.scan("system")
  ctx.refresh()
  // get hold of the actor system
  implicit val system = ctx.getBean(classOf[ActorSystem])
val camel = CamelExtension(system)
  //val camelCXFRest = system.actorOf(Props[CamelCXFRestInterface], name = "CamelCXFRestInterface")
  val camelCXFRest = system.actorOf(Props[CamelCXFRestInterface], name = "CamelCXFRestInterface")
  camel.context.addRoutes(new CustomRouteBuilder(system, camelCXFRest))
}