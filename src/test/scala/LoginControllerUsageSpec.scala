import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

/**
  * Created by puspendu on 8/11/16.
  */
class LoginControllerUsageSpec extends WordSpec with Matchers with BeforeAndAfterAll {

  def failWithUnimplemented = fail("Not Yet Implemented")

  def login(user: String, pass: String): String = {
    var retVal = ""
    if (user.equalsIgnoreCase("aaron")) {
      if (pass.equals("FEQCXKSHJS")) {
        retVal = "Logged In"
      }
    }
    retVal
  }


  "A Login Module " should {

    "Log in with a valid user name and password " in {
      val user = "aaron"
      val pass = "FEQCXKSHJS"

      login(user, pass) should include("Logged In")
    }


  }
}
