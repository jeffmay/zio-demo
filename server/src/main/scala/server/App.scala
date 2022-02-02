package com.rallyhealth.pzn.io
package server

import zhttp.http._
import zhttp.service.Server
import zio.{ZEnv, ZIO, ZIOAppArgs, ZIOAppDefault}

object App extends ZIOAppDefault {

  val routes = Http.collect[Request] {
    case Method.GET -> !! / "hello" => Response.text("Hello World!")
  }

  override val run: ZIO[ZEnv with ZIOAppArgs, Any, Any] = Server.start(8090, routes).exitCode
}
