package com.ruimo.recoeng.json

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.IllegalFieldValueException
import Formatters._

case class JsonRequestHeader(
  dateTime: DateTime,
  sequenceNumber: String
)
  
case class JsonRequestPaging(
  offset: Int,
  limit: Int
)

case class JsonRequestCursorPaging(
  cursor: String,
  limit: Int
)

case class SalesItem(
  storeCode: String,
  itemCode: String,
  quantity: Int
) {
  lazy val redisCode: String = storeCode + ":" + itemCode
}

case class OnSalesJsonRequest(
  header: JsonRequestHeader,
  mode: String,
  dateTime: DateTime,
  userCode: String,
  itemList: Seq[SalesItem]
) {
  lazy val tranDateInYyyyMmDd: Int = toYyyyMmDd(dateTime)
}

case class RecommendBySingleItemJsonRequest(
  header: JsonRequestHeader,
  storeCode: String,
  itemCode: String,
  sort: String,
  paging: JsonRequestPaging
) {
  lazy val sortOrder: SortOrder = SortOrder(sort)
}

case class ScoredItem(
  storeCode: String,
  itemCode: String,
  score: Double
)

case class ListItemRecommendBySite(
  header: JsonRequestHeader,
  paging: JsonRequestCursorPaging
)

case class CreateItemRecommendBySite(
  header: JsonRequestHeader,
  storeCode: String,
  itemCode: String,
  itemList: Seq[ScoredItem]
) {
  lazy val itemListAsMap: Map[String, Double] = itemList.map { e => (e.storeCode + ":" + e.itemCode, e.score) }.toMap
}

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
case class Asc(columnName: String) extends SortOrder
case class Desc(columnName: String) extends SortOrder
