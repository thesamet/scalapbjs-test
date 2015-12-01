package com.trueaccord

import utest._
import com.trueaccord.test.MyTest

object JsTest extends TestSuite {
  val tests = TestSuite {

    val t = MyTest().update(
        _.str1 := "foobarbaz",
        _.myInt := 15,
        _.nested.inside := 17,
        _.objectId := ObjectId("my_id")
    )

    'updateWorks {
      assert(t == 
        MyTest(str1 = Some("foobarbaz"), myInt = Some(15), 
               nested = Some(MyTest.Nested(inside = Some(17))),
               objectId = Some(ObjectId("my_id"))
               ))
    }

    'parseFromIsInverseOfByteArray {
      assert(MyTest.parseFrom(t.toByteArray) == t)
    }
  }
}
