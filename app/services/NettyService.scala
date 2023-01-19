package services

import models._
import mutlipicationfactor._
import play.api.Logger

/*
 * @constructor Service used to get translate the request
 */
class NettyService {
  val logger: Logger = Logger(this.getClass)
/*
 * @param request contains the name and SI conversion
 */
  def translate(request :String): UnitResponse = {
    logger.info(s"NettyService request: $request")
  
    val m = convertToValue(request)
    val res = MultiplicationFactor(m(1))
    UnitResponse(request, res.toString)
 }
}
