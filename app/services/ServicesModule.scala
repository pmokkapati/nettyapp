package services

/*
 * @usecase A trait that is used to wire service to implemtation
 */
trait ServicesModule {

  import com.softwaremill.macwire._

  lazy val nettyService = wire[NettyService]

}
