package shap_test

import shap_test.generator.FieldLister
import shapeless._

object Launcher extends App {

  case class Voiture(name: String)

  case class Bateau(name: String, d: String)


  import shap_test.generator.Generator._
  import shap_test.generator.FieldLister._


  case class Sample(a: Int, b: Boolean, c: Double, d: String)


  //println(generatePayload[Bateau].generate)


  //val t = LabelledGeneric[Bateau]

  //println(t.to(Bateau("zer","rer")))


  println(FieldLister[Bateau].list)

  // val gen = LabelledGeneric[Toti]
  // val k = Keys[gen.Repr].apply()

  //println(k.toList)


}
