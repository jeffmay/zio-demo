package com.rallyhealth.pzn.io
package server.consumer

import server.model.HealthPlan

import fs2.Chunk
import zio.interop.catz._
import zio.{Task, ZIO, ZLayer}

case object HealthPlanConsumer extends Consumer[HealthPlanProcessing, Task, HealthPlan] {

  override def consume(
    stream: fs2.Stream[Task, Chunk[Byte]]
  ): HealthPlanProcessing => fs2.Stream[Task, HealthPlan] = { deps =>
    stream.evalMapChunk { bytes =>
      HealthPlanExampleService.process(new String(bytes.toArray)).provide(ZLayer.succeed(deps))
    }
  }
}

object HealthPlanExampleService {

  def process(bodyAsString: String): ZIO[HealthPlanProcessing, Throwable, HealthPlan] =
    ZIO.succeed(HealthPlan("1234", "example"))
}

class HealthPlanProcessing
