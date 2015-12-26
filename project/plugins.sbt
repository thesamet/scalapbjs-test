addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.5")

addSbtPlugin("com.trueaccord.scalapb" % "sbt-scalapb" % "0.5.15")

libraryDependencies ++= Seq(
  "com.github.os72" % "protoc-jar" % "3.0.0-b1"
)
