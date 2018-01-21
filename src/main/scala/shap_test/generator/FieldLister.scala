package shap_test.generator

import shap_test.Launcher.Bateau
import shapeless._
import shapeless.{HList, HNil, Lazy}
import shapeless.labelled.FieldType

trait FieldLister[A] {
  val list: List[String]

}


object FieldLister {

  def apply[A](implicit fl: FieldLister[A]): FieldLister[A] = fl

  implicit def hconsLister[K, H, T <: HList](
    implicit
    hLister: Lazy[FieldLister[H]],
    tLister: FieldLister[T]
  ): FieldLister[FieldType[K, H] :: T] = {
    new FieldLister[FieldType[K, H] :: T] {
      override val list = hLister.value.list ++ tLister.list
    }
  }


  implicit val hnilLister: FieldLister[HNil] = new FieldLister[HNil] {
    override val list = {
      println("HNil")
      Nil
    }
  }


  implicit def genericLister[A, R](
    implicit
    gen: LabelledGeneric.Aux[A, R],
    lister: Lazy[FieldLister[R]]
  ): FieldLister[A] = {
    new FieldLister[A] {
      override val list = {
        println("generidLister")
        lister.value.list
      }
    }
  }


  implicit def primitiveFieldLister[K <: Symbol, H, T <: HList](
    implicit
    witness: Witness.Aux[K],
    tLister: FieldLister[T]
  ): FieldLister[FieldType[K, H] :: T] = {
    new FieldLister[FieldType[K, H] :: T] {
      override val list = {
       println("prmiitive")
        witness.value.name :: tLister.list
      }
    }
  }

}