
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class NicogorySim201512161700 extends Simulation {

  // base url of the target application
  val baseURL = ""

  // urls to be attacked
  var url1 = "http://example.com/"
  var url2 = "http://example.com/xmlrpc.php"

  val httpProtocol = http
    .baseURL(baseURL)
    .inferHtmlResources(BlackList(""".*\.js.*""", """.*\.css.*""", """.*\.gif.*""", """.*\.jpeg.*""", """.*\.jpg.*""", """.*\.ico.*""", """.*\.woff.*""", """.*\.(t|o)tf.*""", """.*\.png.*"""), WhiteList())

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Upgrade-Insecure-Requests" -> "1")

  val scn = scenario("NicogorySim201512161700")
    .repeat(5, "n") {
    exec(
      http("request_root")
        .get(url2)
        .headers(headers_0)
    )
  }
    .pause(1)
    .exec(
    http("request_my_page")
      .post(url2)
      .headers(headers_0)
      .body(ElFileBody("post.xml")).asXML
  )

  setUp(
    scn.inject(
      constantUsersPerSec(1) during(10 seconds)
    )
  ).protocols(httpProtocol)

}


