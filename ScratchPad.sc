import akka.actor.Actor

class Greeter extends Actor {
  var greeting = ""

  def receive = {
    case WhoToGreet(who) => println( s"hello, $who")
  }
}