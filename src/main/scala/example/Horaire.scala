package example

import java.time.{LocalDate, LocalTime}

import com.timeout.docless.schema.JsonSchema
import io.circe.syntax._



sealed trait Horaire {
  def date: LocalDate
}

case class HoraireFixe(
  date: LocalDate,
  heure: LocalTime,
  tz:TimeZone
) extends Horaire

case class HorairePlage(
  date: LocalDate,
  heureDebut: LocalTime,
  heureFin: LocalTime
) extends Horaire


object Horaire {
  implicit val timeSchema: JsonSchema[LocalTime] =
    JsonSchema.inlineInstance[LocalTime](
      Map(
        "type" -> "string",
        "format" -> "date-time"
      ).asJsonObject
    )
  import com.timeout.docless.schema._

  implicit val h = JsonSchema.deriveFor[Horaire]
}