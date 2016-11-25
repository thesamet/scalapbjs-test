enablePlugins(ScalaJSPlugin)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

name := "ScalaPB on ScalaJS demo"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.trueaccord.scalapb" %%% "scalapb-runtime" % "0.5.45",
  "com.trueaccord.scalapb" %%% "scalapb-runtime" % "0.5.45" % "protobuf",
  "com.lihaoyi" %%% "scalatags" % "0.5.3",
  "com.lihaoyi" %%% "utest" % "0.3.1" % "test"
)

testFrameworks in Test += new TestFramework("utest.runner.Framework")
