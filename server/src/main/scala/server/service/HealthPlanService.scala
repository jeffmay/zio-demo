package com.rallyhealth.pzn.io
package server.service

import server.model.{HealthPlan, HealthPlanV2}

import zio.{ZIO, ZLayer}

object HealthPlanService {

  def process(hp: HealthPlan): ZIO[HealthPlanProcessing, Throwable, Unit] = ZIO.unit

  def processV2(hp: HealthPlanV2): ZIO[HealthPlanProcessing, Throwable, Unit] = ZIO.unit
}

/**
  * An example dependency for any backend services required to process a [[HealthPlan]].
  */
class HealthPlanProcessing

object HealthPlanProcessing {
  val layer = ZLayer.succeed(new HealthPlanProcessing)
}

/**
  * An example dependency for making external API calls to gather more data about a [[HealthPlan]].
  */
class HealthPlanClient

object HealthPlanClient {

  val layer = ZLayer.succeed(new HealthPlanClient)

  def fetchHealthPlanDetails(id: String): ZIO[HealthPlanClient, String, HealthPlanV2] =
    ZIO.fail(s"Could not fetch HealthPlan with id=$id")
}
