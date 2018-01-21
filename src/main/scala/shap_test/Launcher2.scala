package shap_test

import java.time.LocalDate

import shap_test.format.PayloadGenerator
import shapeless.Generic

object Launcher2 extends App {




  import PayloadGenerator._

  case class Partenaire(id: Long, company: String)



  case class Mission(id: Int, name: String, dateCreation: LocalDate,partenaire: Partenaire)

  case class Client(name: String,age: Int)
  case class Payload( client: Client,mission: Mission)




  println(Schema[Payload])



/*{
  "payload" : {
      "mission" : {
  "id":"Int",
  "name":"String",
  "dateCreation" : "LocalDate"
  },
 "client" : {
  "name":"String",
  "age":"Int"
  }
  }
}*/


}
