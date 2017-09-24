package com.trueaccord

import utest._
import com.trueaccord.foods._
import com.trueaccord.advanced._

object JsTest extends TestSuite {
  val tests = TestSuite {

    val myMenu = Menu()
      .update(
        _.menuName := "Our menu",
        _.foods := Seq(
          Food().withName("Apple").withMeasure("1 unit (3\" dia)").withCalories(Calories(95)),
          Food().withName("Chicken McNuggets").withMeasure("4 pieces (64 g)").withCalories(Calories(193)),
          Food().withName("California Roll").withMeasure("8 sushies").withCalories(Calories(400))))

    'updateWorks {
      assert(myMenu.update(_.menuName := "Another menu") ==
             myMenu.copy(menuName = "Another menu"))
    }

    'parseFromIsInverseOfByteArray {
      assert(Menu.parseFrom(myMenu.toByteArray) == myMenu)
    }

    'customOptionWorks {
      assert(
        MyMessage.scalaDescriptor.findFieldByName("my_field").get.getOptions
        .extension(AdvancedProto.foo) == "abcdef")
    }
  }
}
