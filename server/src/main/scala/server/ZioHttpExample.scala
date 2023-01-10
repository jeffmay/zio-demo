package com.rallyhealth.pzn.io
package server

import server.model.HealthPlan
import server.service.{HealthPlanClient, HealthPlanProcessing, HealthPlanService}

import zio._
import zio.http._
import zio.http.model.{HttpError, Method}
import zio.json._

object ZioHttpExample extends ZIOAppDefault {

  final val fakeRoutes: UHttpApp =
    Http.collect[Request] {
      case Method.GET -> !! / "hello" => Response.text("Hello World!")
      case Method.GET -> !! / "healthplan" =>
        val example = HealthPlan("1234", "example health plan")
        Response.json(example.toJsonPretty)
    }

  final val liveRoutes =
    Http.collectZIO[Request] {
      case rq @ Method.GET -> !! / "process" =>
        for {
          id <- ZIO
            .fromOption(rq.url.queryParams.get("id").flatMap(_.headOption))
            .orElseFail(HttpError.BadRequest("Missing 'id' param"))
          hpv2 <- HealthPlanClient.fetchHealthPlanDetails(id).mapError(HttpError.InternalServerError(_, None))
          _ <- HealthPlanService.processV2(hpv2)
        } yield Response.json(hpv2.toJsonPretty)
    }

  final val routes = fakeRoutes ++ liveRoutes

  override val run: ZIO[Any, Throwable, Nothing] =
    Server.serve(routes)
      .provide(
        ServerConfig.live(ServerConfig.default.port(8090)),
        Server.live,
        HealthPlanProcessing.layer,
        HealthPlanClient.layer,
      )
}
