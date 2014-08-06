package com.ruimo.recoeng.json

object SortOrder {
  val SortPattern = """(?i)(asc|desc)\((\w+)\)""".r
  def apply(s: String): SortOrder = s match {
    case SortPattern(order, code) => if (order.toLowerCase == "asc") Asc(code) else Desc(code)
    case _ => throw new IllegalArgumentException("Invalid sort spec '" + s + "'")
  }
}

sealed trait SortOrder {
  val columnName: String
}
case class Asc(columnName: String) extends SortOrder {
  override def toString = "asc(" + columnName + ")"
}
case class Desc(columnName: String) extends SortOrder {
  override def toString = "desc(" + columnName + ")"
}
