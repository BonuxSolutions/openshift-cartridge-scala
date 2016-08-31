package com.bonuxsolutions

import com.bonuxsolutions.Api._
import play.api.libs.json._

import scala.util.Try

trait ApiProtocols {

  implicit val tagFormat = Json.format[Tag]

  implicit object ItemFormat extends Format[Item] {
    override def writes(o: Item): JsValue =
      o match {
        case Items.Group(children) =>
          Json.obj("children" -> children.map(writes))
        case Items.GroupItem(name, measure, price, quantity, tags) =>
          Json.obj(
            "name" -> name,
            "measure" -> measure.toString,
            "price" -> price,
            "quantity" -> quantity,
            "tags" -> tags
          )
      }

    override def reads(json: JsValue): JsResult[Item] =
      for {
        children <- (json \ "children").validateOpt[Seq[Item]]
        nameO <- (json \ "name").validateOpt[Name]
        measureO <- (json \ "measure").validateOpt[String]
        priceO <- (json \ "unitPrice").validateOpt[Price]
        quantityO <- (json \ "quantity").validateOpt[BigDecimal]
        tagsO <- (json \ "tags").validateOpt[Seq[Tag]]
      } yield
        children.map { c =>
          Items.Group(children = c)
        }.getOrElse {
          Items.GroupItem(
            name = nameO.getOrElse(throw new IllegalArgumentException("missing name")),
            measure = measureO.flatMap(MeasurementUnit(_)).getOrElse(throw new IllegalArgumentException(s"invalid measure $measureO")),
            unitPrice = priceO.getOrElse(throw new IllegalArgumentException("missing price")),
            quantity = quantityO.getOrElse(throw new IllegalArgumentException("missing quantity")),
            tags = tagsO.getOrElse(Seq.empty))
        }
  }

}

object ApiProtocolsMain
  extends App
    with ApiProtocols {

  import Items._
  import MeasurementUnit._

  val item1 = GroupItem(
    name = "n1",
    measure = M3,
    unitPrice = 11.0,
    quantity = 2,
    tags = Seq(Tag("t1"), Tag("t2"))
  )
  val item2 = GroupItem(
    name = "n2",
    measure = M3,
    unitPrice = 13.0,
    quantity = 1,
    tags = Seq(Tag("t2"))
  )
  val item3 = GroupItem(
    name = "n3",
    measure = Mon,
    unitPrice = 12.0,
    quantity = 1,
    tags = Seq(Tag("t1"), Tag("t3"))
  )

  val sg = Group(children = Seq(item1, item2))
  val g = Group(children = Seq(sg, item3))

  val json = Json.toJson(g)
  println(json.toString())

  val j = "{\"children\":[{\"children\":[{\"name\":\"n1\",\"measure\":\"M4\",\"price\":11,\"quantity\":2,\"tags\":[{\"value\":\"t1\"},{\"value\":\"t2\"}]},{\"name\":\"n2\",\"measure\":\"M3\",\"price\":13,\"quantity\":1,\"tags\":[{\"value\":\"t2\"}]}]},{\"name\":\"n3\",\"measure\":\"Mon\",\"price\":12,\"quantity\":1,\"tags\":[{\"value\":\"t1\"},{\"value\":\"t3\"}]}]}"

  Try {
    Json.parse(j).as[Item]
  } recover {
    case e: IllegalArgumentException =>
      println(s"The error: ${e.getMessage}")
  }
}