package com.rallyhealth.pzn.io
package server.model

import zio.Random
import zio.test.magnolia.DeriveGen
import zio.test.{Gen, Sized}

object Generators {
  val genHealthPlan: Gen[Random with Sized, HealthPlan] = DeriveGen[HealthPlan]
  val genHealthPlanV2: Gen[Random with Sized, HealthPlanV2] = DeriveGen[HealthPlanV2]
}
