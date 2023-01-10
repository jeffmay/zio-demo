package com.rallyhealth.pzn.io
package server.model

import zio._
import zio.json._
import zio.test._

object HealthPlanFormatSpec extends ZIOSpecDefault {

  override val spec: Spec[TestEnvironment & Scope, Any] = suite("JsonCodec[HealthPlan]") {
    test("bidirectional") {
      check(Generators.genHealthPlan) { hp =>
        val json = hp.toJsonPretty
        val parsed = json.fromJson[HealthPlan]
        println(s"EXAMPLE AS JSON: $json")
        assertTrue(parsed == Right(hp))
      }
    }
  }
}
