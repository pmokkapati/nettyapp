import _root_.controllers.AssetsComponents
import com.softwaremill.macwire._
import play.api.ApplicationLoader.Context
import play.api._
import play.api.i18n._
import play.api.mvc._
import play.api.routing.Router
import router.Routes

/*
 * @constructor The main class to load the application
 * @tparam context specifies the different components used by the application
 */
class NettyApplicationLoader extends ApplicationLoader {
  def load(context: Context): Application = new NettyComponents(context).application
}


/*
 * @constructor  constructor to load different configurations if set in config file
 * @tparam context specifies the different components used by the application
 */
class NettyComponents(context: Context) extends BuiltInComponentsFromContext(context)
    with NettyModule
    with AssetsComponents
    with play.filters.HttpFiltersComponents {

    LoggerConfigurator(context.environment.classLoader).foreach {
      _.configure(context.environment, context.initialConfiguration, Map.empty)
  
}

/*
 * @note Router used to wire actions
 */
  lazy val router: Router = {
    val prefix: String = "/"
    wire[Routes]
 }
}
