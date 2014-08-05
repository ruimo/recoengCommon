package com.ruimo.recoeng.json

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object Formatters {
  val YyyyMmDdFormat: DateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd")
  val YyyyMmDdHhMmSsFormat: DateTimeFormatter = DateTimeFormat.forPattern("yyyyMMddHHmmss")
  def toYyyyMmDd(dateTime: DateTime): Int = YyyyMmDdFormat.print(dateTime).toInt
  def toYyyyMmDd(millis: Long): Int = YyyyMmDdFormat.print(millis).toInt
  def toYyyyMmDdHhMmSs(dateTime: DateTime): String = YyyyMmDdFormat.print(dateTime)
  def toYyyyMmDdHhMmSs(millis: Long): String = YyyyMmDdFormat.print(millis)
}
