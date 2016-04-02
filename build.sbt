import com.trueaccord.scalapb.{ScalaPbPlugin => PB}

enablePlugins(ScalaJSPlugin)

PB.protobufSettings

PB.runProtoc in PB.protobufConfig := (args =>
  com.github.os72.protocjar.Protoc.runProtoc("-v300" +: args.toArray))

name := "ScalaPB on ScalaJS demo"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.trueaccord.scalapb" %%% "scalapb-runtime" % (PB.scalapbVersion in PB.protobufConfig).value,
  "com.trueaccord.scalapb" %%% "scalapb-runtime" % (PB.scalapbVersion in PB.protobufConfig).value % PB.protobufConfig,
  "com.lihaoyi" %%% "scalatags" % "0.5.4",
  "com.lihaoyi" %%% "utest" % "0.4.3" % "test"
)

testFrameworks in Test += new TestFramework("utest.runner.Framework")
