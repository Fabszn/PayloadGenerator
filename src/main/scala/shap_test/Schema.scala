package shap_test

import scala.reflect.ClassTag
import shapeless._, labelled._
import play.api.libs.json._

trait Schema[A] extends DepFn0 {
  type Out <: JsValue
}

trait LowPrioritySchema0 {
  implicit def simpleTypeSchema[A: ClassTag] = new Schema[A]{
    type Out = JsString
    def apply: JsString = JsString(s"${implicitly[ClassTag[A]].runtimeClass.getSimpleName}".capitalize)
  }
}
trait LowPrioritySchema1 extends LowPrioritySchema0 {
  implicit def productTypeSchema[A, R <: HList, O <: JsObject](implicit lgen: LabelledGeneric.Aux[A, R], R: Schema.Aux[R, O]): Schema.Aux[A, O] =
    new Schema[A] {
      type Out = O
      def apply = R()
    }
}

object Schema extends LowPrioritySchema1 {

  type Aux[A, O <: JsValue] = Schema[A]{ type Out = O }

  def apply[A](implicit A: Schema[A]): JsValue = A()

  implicit def hnilSchema = new Schema[HNil] {
    type Out = JsObject
    def apply: JsObject = JsObject.empty
  }

  implicit def hconsSchema[K <: Symbol, V, T <: HList](
    implicit K: Witness.Aux[K],
    vSchema: Lazy[Schema[V]],
    tailSchema: Schema.Aux[T, JsObject]) =
    new Schema[FieldType[K, V] :: T] {
      type Out =  JsObject
      def apply: JsObject = JsObject.apply(Map(K.value.name -> vSchema.value())) ++ tailSchema()
    }

}
