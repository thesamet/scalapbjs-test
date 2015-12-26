import com.trueaccord.scalapb.{ScalaPbPlugin => PB}

enablePlugins(ScalaJSPlugin)

PB.protobufSettings

PB.runProtoc in PB.protobufConfig := (args =>
  com.github.os72.protocjar.Protoc.runProtoc("-v300" +: args.toArray))

name := "ScalaPB on ScalaJS demo"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.trueaccord.lenses" %%% "lenses" % "0.4.4",
  "com.trueaccord.scalapb" %%% "scalapb-runtime" % "0.5.16",
  "com.trueaccord.scalapb" %%% "scalapb-runtime" % "0.5.16" % PB.protobufConfig,
  "com.lihaoyi" %%% "scalatags" % "0.5.3",
  "com.lihaoyi" %%% "utest" % "0.3.1" % "test"
)

testFrameworks in Test += new TestFramework("utest.runner.Framework")
