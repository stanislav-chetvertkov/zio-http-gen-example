ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.1"

lazy val root = (project in file("."))
  .settings(
    name := "zio-http-gen",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-http" % "3.0.0-RC6",
      "dev.zio" %% "zio-http-gen" % "3.0.0-RC6"
    )
  )
