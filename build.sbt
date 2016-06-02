name := "simple-blogging"

version := "0.0-SNAPSHOT"

scalaVersion := "2.11.7"

mainClass in Compile := Some("WebServer")

libraryDependencies ++= {
  val akkaVersion = "2.4.6"
  val longevityVersion = "0.9-SNAPSHOT"
  val scalaTestVersion = "2.2.6"
  val scalaTimeVersion = "2.10.0"
  Seq(
    "com.github.nscala-time" %% "nscala-time" % scalaTimeVersion,
    "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
    "org.longevityframework" %% "longevity" % longevityVersion,
    "org.longevityframework" %% "longevity-cassandra-deps" % longevityVersion,
    "org.longevityframework" %% "longevity-mongo-deps" % longevityVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test
  )
}


fork in run := true