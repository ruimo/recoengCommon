package com.ruimo.recoeng.json

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.IllegalFieldValueException
import Formatters._

case class JsonRequestHeader(
  dateTime: DateTime,
  sequenceNumber: String
) {
  lazy val dateTimeInYyyyMmDdHhMmSs: String = toYyyyMmDdHhMmSs(dateTime)
  lazy val dateTimeInYyyyMmDd: String = toYyyyMmDd(dateTime)
}
  
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
  salesItems: Seq[SalesItem]
) {
  lazy val tranDateInYyyyMmDdHhMmSs: String = toYyyyMmDdHhMmSs(dateTime)
  lazy val tranDateInYyyyMmDd: String = toYyyyMmDd(dateTime)
  lazy val transactionMode: TransactionMode = TransactionMode(mode)
}

case class RecommendByItemJsonRequest(
  header: JsonRequestHeader,
  salesItems: Seq[SalesItem],
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
  salesItems: Seq[ScoredItem]
) {
  lazy val salesItemsAsMap: Map[String, Double] = salesItems.map {
    e => (e.storeCode + ":" + e.itemCode, e.score)
  }.toMap
}

trait JsonResponse {
  def header: JsonResponseHeader
}

case class JsonResponseHeader(
  sequenceNumber: String,
  statusCode: String,
  message: String
)

case class OnSalesJsonResponse(
  header: JsonResponseHeader
) extends JsonResponse

case class RecommendByItemJsonResponse(
  header: JsonResponseHeader,
  salesItems: Seq[ScoredItem],
  sort: String,
  paging: JsonRequestPaging
) extends JsonResponse
