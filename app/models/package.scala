import play.api.libs.json.Reads._
import play.api.libs.json._
import play.api.Logger

package object models {
/** Provides classes and functions used for conversion from and to Json
 *
 *  Also provides implicits for converting Response to and from Json
 */
  implicit val unitResponseWrites = Json.writes[UnitResponse]
  implicit val unitResponseReads = Json.reads[UnitResponse]

  
/** A class to define the response to conversion request
 *
 *  @constructor create a new Response to request
 *  @param unit_name contains the units used to compute the SI Conversion
 *  @param multiplication_factor muupltiplier used to convert units to SI
 */
  case class UnitResponse(unit_name :String, multiplication_factor :String)


/** A class to define the Unit Conversion Factor 
 *
 *  @constructor create a new SI conversion class containing all the converision factors 
 *  @param name name of the convertor
 *  @param symbol symbol that can be used instead of name to retrieve convertor factors
 *  @param quantity SI type
 *  @param conversion SI conversion being used
 *  @param value SI units for the SI name
 *  @param URLEncoded Encoding used to express squares, degrees etc in http requests
 */
case class SIConversion(
        name: String, 
        symbol: String, 
        quantity: String, 
        conversion: String,
        value:BigDecimal, 
        URLEncoded: Option[String]=None) 

  
  private val pie:Double = 3.141592653589793238


  /**
   * List of all SIConversions 
   * These are order descending by name and symbol so to prevent any wrong subsitions 
   * based on matching criteria when data is replaced
   */
  val nameConvertor:List[SIConversion] = List(
      SIConversion("minute", "min", "time", "s", 60),
      SIConversion("hour", "h", "time", "s", 3600),
      SIConversion("day", "d", "time", "s", 86400),
      SIConversion("degree", "°", "unitless/plane angle", "rad", (pie/180), Some("%CF%80")),
      SIConversion("arcminute", ",", "unitless/plane angle", "rad", (pie/10800), Some("%CF%80")),
      SIConversion("arcsecond", "\"", "unitless/plane angle", "rad", (pie/64800), Some("%CF%80")),
      SIConversion("hectare", "ha", "area", "m²", 10000, Some("m%C2%B2")),
      SIConversion("litre", "L", "volume", "m³", 0.001, Some("m%C2%B3")),
      SIConversion("tonne", "t", "mass", "kg", 1000)).sortBy(_.name.length).reverse

  val symbolConvertor = nameConvertor.sortBy(_.symbol.length).reverse

  /**
   * Gets unit name and multiplication factors
   */
  def convertToValue(unitString: String): List[String] = {
    var units:String = unitString.filterNot(a => a == ' ' || a == '(' || a == ')')
    var value:String = units
    nameConvertor.foreach {
      x => (units = units.replaceAll(x.name,x.conversion),
       value = value.replaceAll(x.name, x.value.toString))
    } 
    symbolConvertor.foreach {
      x => (value = value.replaceAll(x.symbol, x.value.toString))
    } 
    List(s"($units)", value)
  }

}
