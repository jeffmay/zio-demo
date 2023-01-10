package com.rallyhealth.pzn.io
package server.model

import zio.test.Gen
import zio.test.magnolia.DeriveGen

object Generators {
  val genHealthPlan: Gen[Any, HealthPlan] = DeriveGen[HealthPlan]
  val genHealthPlanV2: Gen[Any, HealthPlanV2] = DeriveGen[HealthPlanV2]
}
