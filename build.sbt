import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.11.9",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "test-docless",
    libraryDependencies ++= Seq("com.timeout" %% "docless" % "0.6.0-SNAPSHOT",
      "com.chuusai" %% "shapeless" % "2.3.3",
      "com.typesafe.play" %% "play-json" % "2.6.7"
    )
  )
