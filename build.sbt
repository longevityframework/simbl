name := "simple-blogging"

version := "0.1"

scalaVersion := "2.12.0"

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
  val akkaHttpVersion = "10.0.0"
  val longevityVersion = "0.18.0"
  val scalaTestVersion = "3.0.1"
  val scalaTimeVersion = "2.14.0"
  val slf4jSimpleVersion = "1.7.21"
  Seq(
    "org.slf4j"              %  "slf4j-simple"             % slf4jSimpleVersion,
    "com.github.nscala-time" %% "nscala-time"              % scalaTimeVersion,
    "com.typesafe.akka"      %% "akka-http"                % akkaHttpVersion,
    "de.heikoseeberger"      %% "akka-http-json4s"         % "1.11.0",
    "org.longevityframework" %% "longevity"                % longevityVersion,
    "org.longevityframework" %% "longevity-cassandra-deps" % longevityVersion,
    "org.longevityframework" %% "longevity-mongo-deps"     % longevityVersion,
    "org.scalatest"          %% "scalatest"                % scalaTestVersion % Test
  )
}

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

fork in run := true
