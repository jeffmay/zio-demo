package com.rallyhealth.pzn.io
package server.model

import zio.ZIO
import zio.test._

object HealthPlanSchemaSpec extends ZIOSpecDefault {
  override val spec: ZSpec[TestEnvironment, Any] = suite("Schema[HealthPlanV2]") {
    test("migrate to HealthPlan") {
      checkAll(Generators.genHealthPlanV2) { hpv2 =>
        val expected = HealthPlan(hpv2.id, hpv2.name)
        for {
          migrator <- ZIO.fromEither(HealthPlanV2.schema.migrate(HealthPlan.schema))
          observed <- ZIO.fromEither(migrator(hpv2))
        } yield {
          assertTrue(observed == expected)
        }
      }
    }
  }
}
