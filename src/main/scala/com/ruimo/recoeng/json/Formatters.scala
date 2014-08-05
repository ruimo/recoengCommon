package com.ruimo.recoeng.json

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object Formatters {
  val YyyyMmDdFormat: DateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd")
  val YyyyMmDdHhMmSsFormat: DateTimeFormatter = DateTimeFormat.forPattern("yyyyMMddHHmmss")
  def toYyyyMmDd(dateTime: DateTime): String = YyyyMmDdFormat.print(dateTime)
  def toYyyyMmDd(millis: Long): String = YyyyMmDdFormat.print(millis)
  def toYyyyMmDdHhMmSs(dateTime: DateTime): String = YyyyMmDdFormat.print(dateTime)
  def toYyyyMmDdHhMmSs(millis: Long): String = YyyyMmDdFormat.print(millis)
}
