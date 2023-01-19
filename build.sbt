lazy val root = (project in file("."))
  .enablePlugins(PlayScala, PlayNettyServer)
  .disablePlugins(PlayAkkaHttpServer)
  .settings(
    name := """nettymacwireapp""",
    maintainer := "prasadm80@gmail.com",
    version := "2.8.x",
    scalaVersion := "2.13.10",
    PlayKeys.devSettings := Seq("play.server.http.port" -> "8080"),
    libraryDependencies ++= Seq(
      "com.softwaremill.macwire" %% "macros" % "2.5.8" % "provided",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % "test"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      """-Xfatal-warnings"""
    )
  )
