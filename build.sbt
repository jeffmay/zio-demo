import Dependencies._

name := "zio-demo"
ThisBuild / organization := "com.rallyhealth.personalization"
ThisBuild / scalaVersion := "2.13.8"

// Avoid publishing the root aggregate project
publish / skip := true

ThisBuild / scalacOptions ++= Seq(
  "-explaintypes",
  "-unchecked",
  "-Ymacro-annotations",
  "-Ywarn-dead-code",
) ++ Option(
  Seq(
    "infer-any",
    "nullary-override",
    "nullary-unit",
  ).mkString(",")
).filter(_.isEmpty)
  .map(lint => s"-Xlint:$lint")

// SNAPSHOT versions should never be included in non-SNAPSHOT versions of this project
// You should put a BLOCKER on any pull request that does not have the following line of code commented out
// ThisBuild / resolvers += rallyArtifactoryLibSnapshotsResolver.value

lazy val interface = (project in file("interface")).settings(
  name := "lib-pzn-io-interface",
  idePackagePrefix.withRank(KeyRanks.Invisible) := Some("com.rallyhealth.pzn.io"),
  libraryDependencies ++= Api.deps,
)

lazy val circe = (project in file("zio"))
  .dependsOn(interface % "compile;test->test")
  .settings(
    name := "lib-pzn-io-zio",
    idePackagePrefix.withRank(KeyRanks.Invisible) := Some("com.rallyhealth.pzn.io"),
    libraryDependencies ++= Zio.deps,
  )

lazy val server = (project in file("server")).settings(
  name := "lib-pzn-io-server",
  idePackagePrefix.withRank(KeyRanks.Invisible) := Some("com.rallyhealth.pzn.io"),
  libraryDependencies ++= Server.deps,
  // Don't kill sbt when killing the server
  run / fork := true,
  // We publish these test artifacts in our compile jars so that they can be pulled into test projects.
  // TODO: Is this needed?
  // Compile / rallyVerifyDependenciesNoTestJars := {},
)

