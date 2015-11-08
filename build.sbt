import com.trueaccord.scalapb.{ScalaPbPlugin => PB}

enablePlugins(ScalaJSPlugin)

PB.protobufSettings

name := "ScalaPB on ScalaJS demo"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.trueaccord.lenses" %%% "lenses" % "0.4.4",
  "com.trueaccord.scalapb" %%% "scalapb-runtime" % "0.5.15",
  "com.lihaoyi" %%% "utest" % "0.3.1" % "test"
)

testFrameworks in Test += new TestFramework("utest.runner.Framework")
