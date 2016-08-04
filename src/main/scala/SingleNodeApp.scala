import akka.actor.{ActorSystem, Props}
import com.me.finalization.{RestInterface, RestService}

/**
  * Created by puspendu on 8/1/16.
  */
object SingleNodeApp extends App{
    implicit val system = ActorSystem("sorter")
    system.actorOf(Props(new RestService(8080)),"restservice")
}