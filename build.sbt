name := "simple-blogging"

version := "0.0-SNAPSHOT"

scalaVersion := "2.11.7"

mainClass in Compile := Some("SimpleBlogging")

libraryDependencies ++= Seq(
  "org.longevityframework" %% "longevity" % "0.8.0",
  "org.longevityframework" %% "longevity-cassandra-deps" % "0.8.0",
  "org.longevityframework" %% "longevity-mongo-deps" % "0.8.0",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)
