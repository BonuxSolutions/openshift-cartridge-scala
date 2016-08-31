package com.bonuxsolutions

import java.time.LocalDateTime

object Api {

  sealed trait MeasurementUnit

  object MeasurementUnit {

    case object Mon extends MeasurementUnit

    case object M3 extends MeasurementUnit

    case object M2 extends MeasurementUnit

    case object M extends MeasurementUnit

    def units = Seq(Mon, M3, M2, M)

    def apply(unit: String): Option[MeasurementUnit] = units.find(_.toString == unit)
  }

  final case class Tag(value: String) extends AnyVal

  type Price = BigDecimal

  sealed trait Item {
    def sum: Price

    def tags: Seq[Tag]
  }

  object Items {

    final case class Group(children: Seq[Item]) extends Item {
      override def sum: Price = children.map(_.sum).sum

      override def tags: Seq[Tag] = children.flatMap(_.tags)
    }

    final case class GroupItem(name: String,
                               measure: MeasurementUnit,
                               unitPrice: Price,
                               quantity: BigDecimal = 0.0,
                               tags: Seq[Tag] = Seq.empty) extends Item {
      override def sum: Price = unitPrice * quantity
    }

  }

  type Name = String
  type BankAccount = String
  type UID = String

  final case class Address(street: String, house: Int, flatNr: Int)

  sealed trait Entity {
    def name: Name

    def bankAccount: BankAccount

    def uid: UID

    def address: Address
  }

  object Entities {

    final case class Person(firstName: String,
                            lastName: String,
                            bankAccount: BankAccount,
                            personNumber: UID,
                            address: Address) extends Entity {
      override def name: Name = s"$firstName $lastName"

      override def uid: UID = personNumber
    }

    final case class Organization(name: Name,
                                  bankAccount: BankAccount,
                                  registrationNumber: UID,
                                  address: Address) extends Entity {
      override def uid: UID = registrationNumber
    }

  }

  sealed trait State

  object State {

    case object Draft extends State

    case object Final extends State

    case object Cancelled extends State

    case object Closed extends State

    def states = Seq(Draft, Final, Cancelled, Closed)

    def apply(state: String): Option[State] = states.find(_.toString == state)

  }

  final case class Invoice(recipient: Entity,
                           supplier: Entity,
                           invoiceDate: LocalDateTime = LocalDateTime.now(),
                           items: Seq[Item] = Seq.empty,
                           state: State)

}
