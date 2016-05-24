
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class NicogorySim201512161700 extends Simulation {

  // base url of the target application
  val baseURL = ""

  // urls to be attacked
  var url1 = "/"
  var url2 = "/my_page"

  val httpProtocol = http
    .baseURL(baseURL)
    .inferHtmlResources(BlackList(""".*\.js.*""", """.*\.css.*""", """.*\.gif.*""", """.*\.jpeg.*""", """.*\.jpg.*""", """.*\.ico.*""", """.*\.woff.*""", """.*\.(t|o)tf.*""", """.*\.png.*"""), WhiteList())

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Upgrade-Insecure-Requests" -> "1")

  val scn = scenario("NicogorySim201512161700")
    .exec(
      http("request_root")
        .get(url1)
        .headers(headers_0)
    )
    .pause(1)
    .exec(
      http("request_my_page")
        .get(url2)
        .headers(headers_0)
    )

  setUp(
    scn.inject(
      constantUsersPerSec(100) during(10 seconds)
    )
  ).protocols(httpProtocol)

}
