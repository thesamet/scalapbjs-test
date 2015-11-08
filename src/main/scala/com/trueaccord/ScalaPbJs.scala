package com.trueaccord;

import scala.scalajs.js.JSApp
import com.trueaccord.test.MyTest

object ScalaPbJs extends JSApp {
    def main(): Unit = {
        val t = MyTest().update(
            _.str1 := "foobarbaz",
            _.myInt := 15,
            _.nested.inside := 17
        )
        println(t)
        println(t.toByteArray.mkString("[",",","]"))
        val k = MyTest.parseFrom(t.toByteArray)
        println(k)
        println(k == t)
    }
}
