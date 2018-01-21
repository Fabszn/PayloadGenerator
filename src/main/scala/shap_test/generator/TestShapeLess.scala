package shap_test.generator


import shapeless.ops.hlist
import shapeless.ops.record.Keys
import shapeless.{HList, _}


trait Generator[A] {
  def generate: String
}

object Generator {

  def generatePayload[A](implicit gen: Generator[A]): Generator[A] = gen

  implicit def genericToGenerator[A, R <: HList, K <: HList](
    implicit generic: LabelledGeneric.Aux[A, R])

  : Generator[A] = {
    println("TestgenericToGenerator")
    new Generator[A] {
      override def generate = s"key "
    }
  }

  implicit def hconsGenerator[H, T <: HList](
    implicit headGen: Generator[H],
    tailGen: Generator[T]) = {
    new Generator[H :: T] {
      println("hconsGenerator")
      override def generate = "{headGen.generate :: tailGen.generate}"
    }
  }


  implicit def hnilFunc[HNil] = {
    new Generator[HNil] {
      override def generate: String = {
        println("hnilFunc")
        ""
      }
    }
  }

  /*implicit def genericToGenerator[A, R <: HList, K <: HList](
    implicit generic: LabelledGeneric.Aux[A, R],
    keys: Keys.Aux[R, K],
    ktl: hlist.ToList[K, Symbol])
  : Generator[A] = {
    println("TestgenericToGenerator")
    new Generator[A] {
      override def generate = s"keys = ${keys()} - ktl = ${ktl(keys())}"
    }
  }*/


  implicit def genericToGenerator[A](
    implicit fieldLister: FieldLister[A])
  : Generator[A] = {
    println("genericToGenerator")
    new Generator[A] {
      override def generate = s"keys = ${fieldLister.list} "
    }
  }

}
