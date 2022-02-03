package com.rallyhealth.pzn.io
package server.consumer

import server.model.HealthPlan
import server.service.{HealthPlanProcessing, HealthPlanService}

import fs2.Chunk
import zio.interop.catz._
import zio.json._
import zio.{Task, ZIO, ZLayer}

case object HealthPlanConsumer extends Consumer[HealthPlanProcessing, Task, HealthPlan] {

  override def consume(
    stream: fs2.Stream[Task, Chunk[Byte]]
  ): HealthPlanProcessing => fs2.Stream[Task, HealthPlan] = { deps =>
    stream.evalMapChunk { bytes =>
      for {
        hpJson <- ZIO(new String(bytes.toArray))
        hp <- ZIO.fromEither(hpJson.fromJson[HealthPlan]).mapError(new ConsumerParseError(_))
        _ <- HealthPlanService.process(hp).provide(ZLayer.succeed(deps))
      } yield hp
    }
  }
}
