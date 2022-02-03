package com.rallyhealth.pzn.io
package server

import server.model.HealthPlan
import server.service.{HealthPlanClient, HealthPlanProcessing, HealthPlanService}

import zhttp.http._
import zhttp.service.Server
import zio.json._
import zio.{ZEnv, ZIO, ZIOAppArgs, ZIOAppDefault}

object ZioHttpExample extends ZIOAppDefault {

  val routes = {
    Http.collect[Request] {
      case Method.GET -> !! / "hello" => Response.text("Hello World!")
      case Method.GET -> !! / "healthplan" =>
        val example = HealthPlan("1234", "example health plan")
        Response.json(example.toJsonPretty)
    } ++ Http.collectZIO[Request] { case rq @ Method.GET -> !! / "process" =>
      for {
        id <- ZIO
          .fromOption(rq.url.queryParams.get("id").flatMap(_.headOption))
          .orElseFail(HttpError.BadRequest("Missing 'id' param"))
        hpv2 <- HealthPlanClient.fetchHealthPlanDetails(id).mapError(HttpError.InternalServerError(_, None))
        _ <- HealthPlanService.processV2(hpv2)
      } yield Response.json(hpv2.toJsonPretty)
    }
  }

  override val run: ZIO[ZEnv with ZIOAppArgs, Any, Any] =
    Server
      .start(8090, routes)
      .provide(HealthPlanProcessing.layer, HealthPlanClient.layer)
}
