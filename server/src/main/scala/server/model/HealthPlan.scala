package com.rallyhealth.pzn.io
package server.model

import zio.json._
import zio.schema.{DeriveSchema, Schema}

import java.time.LocalDate

final case class HealthPlan(id: String, name: String)
object HealthPlan {
  implicit lazy val jsonCodec: JsonCodec[HealthPlan] = DeriveJsonCodec.gen
  implicit lazy val schema: Schema[HealthPlan] = DeriveSchema.gen
}

final case class HealthPlanV2(id: String, name: String, version: String, startDate: LocalDate)
object HealthPlanV2 {
  implicit lazy val jsonCodec: JsonCodec[HealthPlanV2] = DeriveJsonCodec.gen
  implicit lazy val schema: Schema[HealthPlanV2] = DeriveSchema.gen
}
