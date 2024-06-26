name := "users-app"
version := "1.0"
scalaVersion := "2.13.10"

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

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-lang3" % "3.12.0",
  "com.twitter" %% "finagle-http" % "21.6.0",
  "org.slf4j" % "slf4j-simple" % "2.0.13",
  "org.slf4j" % "slf4j-api" % "2.0.13",
  "io.circe" %% "circe-core" % "0.14.5",
  "io.circe" %% "circe-generic" % "0.14.5",
  "io.circe" %% "circe-parser" % "0.14.5"
)
