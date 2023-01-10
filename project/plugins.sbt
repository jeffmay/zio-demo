// See https://wiki.audaxhealth.com/display/ENG/Build+Structure#BuildStructure-Localconfiguration
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

resolvers += "Optum Digital Releases".at(s"https://artifacts.werally.in/artifactory/optum-digital-maven-release")

// Keep ordered alphabetically
addSbtPlugin("com.rallyhealth" %% "rally-sbt-plugin" % "1.6.0")
addSbtPlugin("com.rallyhealth" %% "rally-versioning" % "1.13.0")
addSbtPlugin("com.rallyhealth.sbt" %% "rally-shading-sbt-plugin" % "1.4.0")
addSbtPlugin("org.jetbrains.scala" % "sbt-ide-settings" % "1.1.1")
addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "2.0.6")

