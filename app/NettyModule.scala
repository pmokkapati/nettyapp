import controllers.NettyController
import play.api.mvc.ControllerComponents
import services.ServicesModule

/**
 * Trait used to set up macwire dependency injection
 */
trait NettyModule extends ServicesModule {

  import com.softwaremill.macwire._

  lazy val nettyController = wire[NettyController]

  def controllerComponents: ControllerComponents
}
