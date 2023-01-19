import org.scalatestplus.play.{BaseOneAppPerTest, PlaySpec}
import play.api.test.Helpers._
import play.api.test._

/**
 *
 */
class ApplicationSpec extends PlaySpec
  with BaseOneAppPerTest
  with NettyApplicationFactory {

  "Routes" should {
    "send 404 on a bad request" in {
      route(app, FakeRequest(GET, "/units")).map(status(_)) mustBe Some(NOT_FOUND)
    }
  }
}
