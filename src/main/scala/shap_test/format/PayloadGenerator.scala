package shap_test.format

import shapeless.ops.record.Keys
import shapeless.{HList, HNil, _}

trait PayloadGenerator[A] {

  def format(value: String): String

}


object PayloadGenerator {

  def apply[A](implicit pGene: PayloadGenerator[A]): PayloadGenerator[A] = pGene


  def createFormater[A](func: String => String): PayloadGenerator[A] = {
    new PayloadGenerator[A] {
      def format (fieldName: String): String = func(fieldName)
    }
  }
  implicit def genericPayload[A, R](
    implicit
    lg: Generic.Aux[A, R],
    p: PayloadGenerator[R]):PayloadGenerator[R] = {
    createFormater[R](v => "test")

  }


  implicit val stringGen = createFormater[String](str => s"$str : String")

  implicit val longGen = createFormater[Long](lg => s"$lg : Long")
  implicit val IntGen = createFormater[Int](lg => s"$lg : Int")


  implicit def payloadHNil = {
    new PayloadGenerator[HNil] {
      override def format(fname: String): String = "End"
    }
  }

  implicit def consPayLoad[H, T <: HList](
    implicit
    head: PayloadGenerator[T],
    tail: PayloadGenerator[H]) = {
    createFormater[H :: T](v => s"${head.format("")} ${tail.format("")}" )


  }





}