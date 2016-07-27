addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.11")

addSbtPlugin("com.trueaccord.scalapb" % "sbt-scalapb" % "0.5.34")

libraryDependencies ++= Seq(
  "com.github.os72" % "protoc-jar" % "3.0.0-b3"
)
