package system

import akka.actor.ExtendedActorSystem
import akka.camel.ContextProvider
import org.apache.camel.spring.SpringCamelContext

/**
  * Created by 258265 on 9/6/2016.
  */
class SpringCamelContextProvider extends ContextProvider{
  override def getContext(system: ExtendedActorSystem): SpringCamelContext =new SpringCamelContext(SpringExtension.SpringExtProvider.get(system).applicationContext)
}
