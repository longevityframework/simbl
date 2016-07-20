name := "simple-blogging"

version := "0.0-SNAPSHOT"

scalaVersion := "2.11.8"

mainClass in Compile := Some("simbl.api.WebServer")

resolvers += Resolver.bintrayRepo("hseeberger", "maven")

scalacOptions ++= Seq(
  "-Xfuture",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused-import",
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked")

libraryDependencies ++= {
  val akkaVersion = "2.4.7"
  val longevityVersion = "0.9.0"
  val scalaTestVersion = "2.2.6"
  val scalaTimeVersion = "2.10.0"
  Seq(
    "com.github.nscala-time" %% "nscala-time" % scalaTimeVersion,
    "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
    "de.heikoseeberger" %% "akka-http-json4s" % "1.6.0",
    "org.longevityframework" %% "longevity" % longevityVersion,
    "org.longevityframework" %% "longevity-cassandra-deps" % longevityVersion,
    "org.longevityframework" %% "longevity-mongo-deps" % longevityVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test
  )
}


fork in run := true
