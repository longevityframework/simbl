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
  val akkaHttpVersion       = "10.0.3"
  val akkaHttpJson4sVersion = "1.12.0"
  val longevityVersion      = "0.24.0"
  val scalaTestVersion      = "3.0.1"
  val scalaTimeVersion      = "2.16.0"
  val slf4jSimpleVersion    = "1.7.25"
  Seq(
    "org.slf4j"              %  "slf4j-simple"             % slf4jSimpleVersion,
    "com.github.nscala-time" %% "nscala-time"              % scalaTimeVersion,
    "com.typesafe.akka"      %% "akka-http"                % akkaHttpVersion,
    "de.heikoseeberger"      %% "akka-http-json4s"         % akkaHttpJson4sVersion,
    "org.longevityframework" %% "longevity"                % longevityVersion,
    "org.longevityframework" %% "longevity-cassandra-deps" % longevityVersion,
    "org.longevityframework" %% "longevity-mongodb-deps"   % longevityVersion,
    "org.longevityframework" %% "longevity-sqlite-deps"    % longevityVersion,
    "org.scalatest"          %% "scalatest"                % scalaTestVersion % Test
  )
}

// libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.4.17"
// libraryDependencies += "co.fs2" %% "fs2-core" % "0.9.4"
// libraryDependencies += "org.typelevel" %% "cats" % "0.9.0"
// libraryDependencies += "io.iteratee" %% "iteratee-core" % "0.10.0"
// libraryDependencies += "com.typesafe.play" %% "play-iteratees" % "2.6.1"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

fork in run := true
