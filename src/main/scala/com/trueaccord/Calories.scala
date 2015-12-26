package com.trueaccord

case class Calories(value: Int) extends AnyVal

object Calories {
  implicit val typeMapper =
    com.trueaccord.scalapb.TypeMapper(Calories.apply)(_.value)
}

