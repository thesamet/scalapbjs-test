package com.trueaccord

import com.trueaccord.foods.{Food, Menu}
import com.trueaccord.lenses.{Mutation, Lens}
import org.scalajs.dom.Event
import scalatags.JsDom.all._

import scala.util.Try

object FoodHtmlBindings {
  private def l = Lens.unit[Menu]

  trait InputConverter[A] {
    def fromString(s: String): A

    def toString(a: A): String
  }

  implicit object StringConverter extends InputConverter[String] {
    def fromString(s: String) = s

    def toString(a: String) = a
  }

  implicit object IntConverter extends InputConverter[Calories] {
    def fromString(s: String) = Calories(Try(s.toInt).getOrElse(0))

    def toString(a: Calories) = a.value.toString
  }

  def lensInput[Container, A](container: Container,
                              lens: Lens[Container, A],
                              onChange: Mutation[Container] => Unit,
                              inputType: String = "text")(implicit t: InputConverter[A]) = {
    val html = input(`type` := inputType, `class` := "form-control",
      value := t.toString(lens.get(container))).render

    val notifyFunction = { e: Event => onChange(lens.set(t.fromString(html.value))) }
    html.onkeyup = notifyFunction
    html.onmouseup = notifyFunction
    html
  }

  def foodComponent(myMenu: Menu, index: Int, onChange: Mutation[Menu] => Unit) = {
    val removeButton = button("(x)", `class` := "btn btn-danger", onclick := { e: Event => onChange(
      l.foods.modify(foods => foods.take(index) ++ foods.drop(index + 1)))
    })

    tr(
      td(lensInput(myMenu, l.foods(index).name, onChange)),
      td(lensInput(myMenu, l.foods(index).measure, onChange)),
      td(lensInput(myMenu, l.foods(index).calories, onChange, inputType = "number")),
      td(removeButton)
    ).render
  }

  def menuComponent(myMenu: Menu, onChange: Mutation[Menu] => Unit) = {
    val nameInput = lensInput(myMenu, l.menuName, onChange)
    val foods = (0 until myMenu.foods.size).map(foodComponent(myMenu, _, onChange))

    div(
      nameInput,
      table(`class` := "table table-striped",
        thead(tr(th("Food"), th("Measure"), th("Calories"))),
        tbody(foods)
      ),
      button(`class` := "btn btn-primary", "Add", onclick := {
        e: Event => onChange(l.foods :+= Food())
      })
    ).render
  }
}
