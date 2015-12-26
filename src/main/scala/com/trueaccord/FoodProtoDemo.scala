package com.trueaccord

import com.trueaccord.foods.{Food, Menu}
import com.trueaccord.lenses.Mutation
import org.scalajs.dom.html

import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

@JSExport
object FoodProtoDemo extends {
  @JSExport
  def main(target: html.Div) = {
    val menuBlock = div().render
    val bytesBlock = pre().render
    val parsedBlock = pre().render

    def updateRightSide(m: Menu): Unit = {
      def bytesToHex(bytes: Array[Byte]): String =
        bytes.map(b => String.format("%02x", new Integer(b.toInt & 0xff))).mkString(" ")

      val bytes = m.toByteArray
      bytesBlock.textContent = bytes.grouped(20).map(bytesToHex).mkString("\n")
      parsedBlock.textContent = Menu.parseFrom(bytes).toString
    }

    var myMenu = Menu()
      .update(
        _.menuName := "Our menu",
        _.foods := Seq(
          Food().withName("Apple").withMeasure("1 unit (3\" dia)").withCalories(Calories(95)),
          Food().withName("Chicken McNuggets").withMeasure("4 pieces (64 g)").withCalories(Calories(193)),
          Food().withName("California Roll").withMeasure("8 sushies").withCalories(Calories(400))))

    def updateMenuBlock(): Unit = {
      menuBlock.innerHTML = ""
      val mb = FoodHtmlBindings.menuComponent(myMenu, onChange)
      menuBlock.appendChild(mb)
    }

    def onChange(mutation: Mutation[Menu]): Unit = {
      val oldMenu = myMenu
      myMenu = mutation(myMenu)
      if (myMenu.foods.size != oldMenu.foods.size) {
        updateMenuBlock()
      }
      updateRightSide(myMenu)
    }

    updateRightSide(myMenu)
    updateMenuBlock()

    target.appendChild(
      div(
        h1("ScalaPB with ScalaJS demo"),
        div(`class` := "col-md-6",
          menuBlock),
        div(`class` := "col-md-6",
          p("Proto:"),
          pre(
            """|syntax = "proto3";
               |
               |package com.trueaccord;
               |
               |import "scalapb/scalapb.proto"
               |
               |message Food {
               |  string name = 1;
               |  string measure = 2;
               |  int32 calories = 3 [(scalapb.field).type = "com.trueaccord.Calories"];
               |}
               |
               |message Menu {
               |  string menu_name = 1;
               |  repeated Food foods = 2;
               |}""".stripMargin),
          p("toByteArray (presented as hex):"),
          bytesBlock,
          p("Byte array parsed back to a case class:"),
          parsedBlock)
      ).render
    )
  }
}
