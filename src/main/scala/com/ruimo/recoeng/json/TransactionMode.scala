package com.ruimo.recoeng.json

import scala.collection.immutable

sealed trait TransactionMode {
  def asString: String
}

case object TransactionSalesMode extends TransactionMode {
  val asString = "0001"
}

object TransactionMode {
  val asMap: immutable.Map[String, TransactionMode] = Seq(
    TransactionSalesMode
  ).map { e => (e.asString, e) }.toMap

  def apply(s: String): TransactionMode = asMap(s)
}
