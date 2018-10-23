package models

import play.api.data._
import play.api.data.Forms._

import scala.collection.mutable.ArrayBuffer

case class People (fName:String,lName:String,age:Int)

object People {
  val createPersonForm = Form(
    mapping(
      "fName" -> nonEmptyText,
      "lName" -> nonEmptyText,
      "age" -> number(min = 0, max = 112)
    )(People.apply)(People.unapply)
  )
  val people = ArrayBuffer(
    People("Hugh","Mann",58),
    People("General","Augustine",93),
    People("Suvi","Anwar",26)
  )
}
