package com.rallyhealth.pzn.io
package server

import zhttp.http.{Request, URL}
import zio.test._

import scala.io.Codec

object ExampleZioAppSuite extends ZIOSpecDefault {

  override val spec: ZSpec[TestEnvironment, Any] = suite("ExampleZioApp") {
    test("GET /hello") {
      for {
        rsp <- ExampleZioApp.routes(Request(url = URL(URL.root.path / "hello")))
        body <- rsp.data.toByteBuf
      } yield {
        val bodyAsString = body.toString(Codec.UTF8.charSet)
        assertTrue(bodyAsString == "Hello World!")
      }
    }
  }
}
