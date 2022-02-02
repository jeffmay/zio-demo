package com.rallyhealth.pzn.io
package server.model

import zio.json._
import zio.schema.{DeriveSchema, Schema, StandardType}

import java.time.LocalDate
import java.time.format.DateTimeFormatter

final case class HealthPlan(id: String, name: String)
object HealthPlan {
  implicit lazy val jsonCodec: JsonCodec[HealthPlan] = DeriveJsonCodec.gen
  implicit lazy val schemaLocalData: Schema[LocalDate] = Schema.primitive(StandardType.LocalDate(DateTimeFormatter.ISO_LOCAL_DATE))
  implicit lazy val schema: Schema[HealthPlan] = DeriveSchema.gen
}
