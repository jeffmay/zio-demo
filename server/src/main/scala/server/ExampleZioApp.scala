package com.rallyhealth.pzn.io
package server

import server.model.HealthPlan

import zhttp.http._
import zhttp.service.Server
import zio.json._
import zio.{ZEnv, ZIO, ZIOAppArgs, ZIOAppDefault}

object ExampleZioApp extends ZIOAppDefault {

  val routes = Http.collect[Request] {
    case Method.GET -> !! / "hello" => Response.text("Hello World!")
    case Method.GET -> !! / "healthplan" =>
      val example = HealthPlan("1234", "example health plan")
      Response.json(example.toJsonPretty)
  }

  override val run: ZIO[ZEnv with ZIOAppArgs, Any, Any] = Server.start(8090, routes).exitCode
}
