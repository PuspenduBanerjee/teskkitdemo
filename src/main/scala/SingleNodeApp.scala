import akka.actor.{ActorSystem, Props}
import akka.camel.CamelExtension
import com.me.finalization._
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver

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
  val pmrl:PathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(ctx.getClassLoader())
  val resources = pmrl.getResources(
    "classpath*:**/applicationContext.xml"
  )
  resources.map(r => {
    val reader = new XmlBeanDefinitionReader(ctx);
    val i = reader.loadBeanDefinitions(r);
  })
  ctx.refresh()
  // get hold of the actor system
  implicit val system = ctx.getBean(classOf[ActorSystem])
val camel = CamelExtension(system)
  val camelCXFRest = system.actorOf(Props[CamelCXFRestInterface], name = "CamelCXFRestInterface")
  camel.context.addRoutes(new CustomRouteBuilder(system, camelCXFRest))
}