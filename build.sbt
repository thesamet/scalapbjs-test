import com.trueaccord.scalapb.compiler.Version.scalapbVersion

enablePlugins(ScalaJSPlugin)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

name := "ScalaPB on ScalaJS demo"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.trueaccord.scalapb" %%% "scalapb-runtime" % scalapbVersion,
  "com.trueaccord.scalapb" %%% "scalapb-runtime" % scalapbVersion % "protobuf",
  "com.lihaoyi" %%% "scalatags" % "0.6.3",
  "com.lihaoyi" %%% "utest" % "0.4.5" % "test"
)

testFrameworks in Test += new TestFramework("utest.runner.Framework")
