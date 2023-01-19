package controllers

import play.api.mvc.{Action, AnyContent}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ Future, ExecutionContext }
import play.api.libs.json.Reads._
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.Logger
import services.NettyService
import models._


/**
 * @author Prasad Mokkapati
 * @constructor Netty controller to create a service
 * @param cc is control parameter used for wiring
 * @tparam Control components
 * @return Json mapping of result
 */
class NettyController(nettyService:NettyService)
(cc: ControllerComponents) extends AbstractController(cc) {

 protected val logger: Logger = Logger(this.getClass())

/**
 * @tparam units passed to convert action
 * @throws BadRequest exception if units type is invalid
 */
 def convert(units:String): Action[AnyContent] = Action.async { implicit request =>
    Future(body = try {
      Ok(Json.toJson(nettyService.translate(units)))
    } catch {
      case ex: Throwable =>
        logger.debug(s"Exception on NettyController: ${ex.printStackTrace().toString})")
        BadRequest(s"Invalid data - $units")
    })
 }
}
