package org.kotlin

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import arrow.core.raise.mapOrAccumulate
import arrow.core.raise.zipOrAccumulate
import arrow.core.recover
import arrow.core.toNonEmptyListOrNull

sealed interface BookValidationError
object EmptyTitle: BookValidationError
object NoAuthors: BookValidationError
data class EmptyAuthor(val index: Int): BookValidationError

data class Book private constructor(
    val title: String, val authors: NonEmptyList<Author>
) {
    companion object {
        operator fun invoke(
            title: String, authors: Iterable<String>
        ): Either<NonEmptyList<BookValidationError>, Book> = either {
            zipOrAccumulate(
                { ensure(title.isNotEmpty()) { EmptyTitle } },
                {
                    val validationAuthors = mapOrAccumulate(authors.withIndex()) { nameAndIx ->
                        Author(nameAndIx.value)
                            .recover { _ -> raise(EmptyAuthor(nameAndIx.index)) }
                            .bind()
//                        Author(nameAndIx.value)
//                            .mapLeft { EmptyAuthor(nameAndIx.index) }
//                            .bind()
                    }
                    val validatedAuthors = authors.withIndex().map { nameAndIx ->
                        Author(nameAndIx.value)
                            .mapLeft { EmptyAuthor(nameAndIx.index) }
                    }.bindAll()

                    ensureNotNull(validationAuthors.toNonEmptyListOrNull()) { NoAuthors}
                }
            ) {_, authorsNel ->
                Book(title, authorsNel)
            }
        }
    }
}