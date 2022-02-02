// See https://wiki.audaxhealth.com/display/ENG/Build+Structure#BuildStructure-Localconfiguration
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

resolvers += Resolver.url(
  "Rally Internal Plugin Releases",
  url("https://artifacts.werally.in/artifactory/ivy-plugins-release")
)(Resolver.ivyStylePatterns)

// Keep ordered alphabetically
addSbtPlugin("com.rallyhealth" %% "rally-sbt-plugin" % "0.26.0")
addSbtPlugin("com.rallyhealth" %% "rally-versioning" % "1.10.9")
addSbtPlugin("com.rallyhealth.sbt" %% "rally-shading-sbt-plugin" % "1.3.0")
addSbtPlugin("org.jetbrains.scala" % "sbt-ide-settings" % "1.1.1")
addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "1.9.3")

