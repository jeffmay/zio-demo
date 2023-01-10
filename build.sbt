import Dependencies._

name := "zio-demo"
ThisBuild / organization := "com.rallyhealth.personalization"
ThisBuild / scalaVersion := Scala_2_13

// Avoid publishing the root aggregate project
publish / skip := true

ThisBuild / scalacOptions ++= Seq(
  "-explaintypes",
  "-unchecked",
  "-Wdead-code",
  "-Xlint",
)

ThisBuild / libraryDependencies += compilerPlugin(
  ("org.typelevel" % "kind-projector" % "0.13.2").cross(CrossVersion.full)
)

ThisBuild / idePackagePrefix.withRank(KeyRanks.Invisible) := Some("com.rallyhealth.pzn.io")
ThisBuild / testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

// SNAPSHOT versions should never be included in non-SNAPSHOT versions of this project
// You should put a BLOCKER on any pull request that does not have the following line of code commented out
// ThisBuild / resolvers += rallyArtifactoryLibSnapshotsResolver.value

lazy val interface = (project in file("interface"))
  .settings(
    name := "lib-pzn-io-interface",
    libraryDependencies ++= Api.deps,
  )

lazy val zio = (project in file("zio"))
  .dependsOn(interface)
  .settings(
    name := "lib-pzn-io-zio",
    libraryDependencies ++= Zio.deps,
  )

lazy val server = (project in file("server"))
  .dependsOn(zio)
  .settings(
    name := "lib-pzn-io-server",
    libraryDependencies ++= Server.deps,
    // Don't kill sbt when killing the server
    run / fork := true,
    // We publish these test artifacts in our compile jars so that they can be pulled into test projects.
    // TODO: Is this needed?
    // Compile / rallyVerifyDependenciesNoTestJars := {},
  )
