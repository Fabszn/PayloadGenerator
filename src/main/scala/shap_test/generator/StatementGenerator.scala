package shap_test.generator

import example.Docless.A
import shapeless.ops.hlist
import shapeless.ops.record.Keys
import shapeless.{HList, LabelledGeneric, record}

trait StatementGenerator[A] {
  def show: String
}


object StatementGenerator {
  def apply[A](implicit sg: StatementGenerator[A]): StatementGenerator[A] = sg

  def genericGenerator[A, R <: HList, K <: HList](
    implicit
    gen: LabelledGeneric.Aux[A, R],
    keys: Keys.Aux[R, K],
    ktl: hlist.ToList[K, Symbol]
  ): StatementGenerator[A] = {
    new StatementGenerator[A] {
      override def show: String = s"test ${keys()}"

    }
  }
}