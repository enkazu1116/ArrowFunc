package org.kotlin

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.Raise
import arrow.core.right

object CustomerNotFound
data class Customer(val id: Long)

val customer: Either<CustomerNotFound, Customer> = Customer(1).right()

fun Raise<CustomerNotFound>.customer(): Customer = Customer(1)
val error: Either<CustomerNotFound, Customer> = CustomerNotFound.left()

fun Raise<CustomerNotFound>.error(): Customer = raise(CustomerNotFound)