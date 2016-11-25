addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.1" exclude ("com.trueaccord.scalapb", "protoc-bridge_2.10"))

libraryDependencies += "com.trueaccord.scalapb" %% "compilerplugin-shaded" % "0.5.45-p2"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.13")
