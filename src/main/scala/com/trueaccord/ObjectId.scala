package com.trueaccord

case class ObjectId(id: String) extends AnyVal

object ObjectId {
    implicit val typeMapper =
        com.trueaccord.scalapb.TypeMapper(ObjectId.apply)(_.id)
}

