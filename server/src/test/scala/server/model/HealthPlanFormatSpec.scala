package com.rallyhealth.pzn.io
package server.model

import zio.json._
import zio.test._

object HealthPlanFormatSpec extends ZIOSpecDefault {
  override val spec: ZSpec[TestEnvironment, Any] = suite("HealthPlan") {
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
