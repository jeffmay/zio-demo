import sbt._
import scala.language.postfixOps

object Dependencies {

  private val munit = "org.scalameta" %% "munit" % "1.0.0-M1"

  private val munitScalacheck = "org.scalameta" %% "munit-scalacheck" % "1.0.0-M1"

  private val scalacheck = "org.scalacheck" %% "scalacheck" % "1.15.4"

  private val scalacheckOps = "com.rallyhealth" %% "scalacheck-ops_1-15" % "2.8.1"

  private val zio = "dev.zio" %% "zio" % "2.0.0-RC1"

  private val zioHttp = "io.d11" %% "zhttp" % "2.0.0-RC1"

  private val zioSchema = "dev.zio" %% "zio-schema" % "0.2.0-RC1-1"

  private val zioTest = "dev.zio" %% "zio-test" % "2.0.0-RC1"

  object Api {

    val deps: Seq[ModuleID] = Seq(
      // Test-only dependencies
      munit,
      munitScalacheck,
      scalacheck,
      scalacheckOps,
    ).map(_ % Test)
  }

  object Zio {

    val deps: Seq[ModuleID] = Api.deps ++ Seq(
      zio,
      zioSchema,
    ) ++ Seq(
      // Test-only dependencies
      zioTest,
    ).map(_ % Test)
  }

  object Server {

    val deps: Seq[ModuleID] = Zio.deps ++ Seq(
      zioHttp,
    )
  }
}
