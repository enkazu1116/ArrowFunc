package org.kotlin

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure

object EmptyAuthorName

data class Author private constructor(val name: String) {
    companion object {
        operator fun  invoke(name: String) : Either<EmptyAuthorName, Author> = either  {
            ensure(name.isEmpty()) { EmptyAuthorName }
            Author(name)
        }
    }
}