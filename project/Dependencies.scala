import sbt._
import scala.language.postfixOps

object Dependencies {

  final val Scala_2_13 = "2.13.8"

  private final val zioVersion = "2.0.0-RC1"
  private final val zioSchemaVersion = "0.2.0-RC1-1"

  private val scalaReflect = "org.scala-lang" % "scala-reflect" % Scala_2_13 % "provided"

  private val zio = "dev.zio" %% "zio" % zioVersion

  private val zioJson = "dev.zio" %% "zio-json" % "0.3.0-RC1"

  private val zioHttp = "io.d11" %% "zhttp" % zioVersion

  private val zioSchema = "dev.zio" %% "zio-schema" % zioSchemaVersion

  private val zioSchemaDerivation = "dev.zio" %% "zio-schema-derivation" % zioSchemaVersion

  private val zioSchemaJson = "dev.zio" %% "zio-schema-json" % zioSchemaVersion

  private val zioTest = "dev.zio" %% "zio-test" % zioVersion

  private val zioTestMagnolia = "dev.zio" %% "zio-test-magnolia" % zioVersion

  object Api {

    val deps: Seq[ModuleID] = Seq(
      // Test-only dependencies
      zioTest,
      zioTestMagnolia,
    ).map(_ % Test)
  }

  object Zio {

    val deps: Seq[ModuleID] = Seq(
      zio,
      zioSchema,
    ) ++ Seq(
      // Test-only dependencies
      zioTest,
      zioTestMagnolia,
    ).map(_ % Test)
  }

  object Server {

    val deps: Seq[ModuleID] = Seq(
      scalaReflect,
      zio,
      zioHttp,
      zioJson,
      zioSchema,
      zioSchemaJson,
      zioSchemaDerivation,
    ) ++ Seq(
      // Test-only dependencies
      zioTest,
      zioTestMagnolia,
    ).map(_ % Test)
  }
}
