name := "data-app"
version := "1.0"
scalaVersion := "2.12.10"

Compile / mainClass := Some("Main")
assembly / mainClass := Some("Main")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x                             => MergeStrategy.first
}

// Run in a separate JVM, to make sure sbt waits until all threads have
// finished before returning.
// If you want to keep the application running while executing other
// sbt tasks, consider https://github.com/spray/sbt-revolver/
fork := true

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases/"

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % "21.2.0",
  "com.jakehschwartz" % "finatra-swagger_2.12" % "2.9.0",
  "io.swagger" %% "swagger-scala-module" % "1.0.7-SNAPSHOT"
)
