package example

case class TimeZone(name:String, offset:Int)

object TimeZone{
  import com.timeout.docless.schema._
  implicit val f = JsonSchema.deriveFor[TimeZone]
}