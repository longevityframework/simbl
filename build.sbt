name := "simple-blogging"

version := "0.0-SNAPSHOT"

scalaVersion := "2.11.7"

mainClass in Compile := Some("SimpleBlogging")

libraryDependencies ++= Seq(
  "org.longevityframework" %% "longevity" % "0.9-SNAPSHOT",
  "org.longevityframework" %% "longevity-cassandra-deps" % "0.9-SNAPSHOT",
  "org.longevityframework" %% "longevity-mongo-deps" % "0.9-SNAPSHOT",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)
