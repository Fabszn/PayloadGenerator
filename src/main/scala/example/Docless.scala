package example

import com.timeout.docless.schema.JsonSchema

object Docless extends App {

  sealed trait A

  case class B(s: String, i: Int) extends A

  case class C(d: Double, f: Float, b: Option[Boolean], bb: B) extends A

  // ça passe sur scala 2.12 mais pas sur 2.11
  case class D(a: A)

 /* println(JsonSchema.deriveFor[A])
  implicit val t = JsonSchema.deriveFor[A]
  println(JsonSchema.deriveFor[D])*/


  // import Horaire._ pour inclure l'implicit fornissant JsonSchema[LocalTime]
  // ça passe 2.12
  import Horaire._
  import TimeZone._


  //println(JsonSchema.deriveFor[Horaire].definitions)


  // la même situation que case classe D mais ça passe pas !
  // peut être du fait que Horaire est dans un autre fichier ????!!!!
  case class E(h: TimeZone)
  //println(JsonSchema.deriveFor[Mission].definitions)
}
