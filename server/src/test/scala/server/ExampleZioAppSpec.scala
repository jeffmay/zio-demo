package com.rallyhealth.pzn.io
package server

import zio._
import zio.http._
import zio.test._

object ExampleZioAppSpec extends ZIOSpecDefault {

  override val spec: Spec[TestEnvironment & Scope, Any] = suite("ExampleZioApp") {
    test("GET /hello") {
      for {
        rsp <- ZioHttpExample.fakeRoutes(Request.get(URL(Path.root / "hello")))
        bodyAsString <- rsp.body.asString
      } yield {
        assertTrue(bodyAsString == "Hello World!")
      }
    }
  }
}
