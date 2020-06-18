package domain.models.user

import arrow.core.Either
import domain.models.error.UserError

data class UserEmailAddress private constructor(private val value: String) {
    fun asString(): String = value

    companion object {
        operator fun invoke(value: String): Either<UserError, UserEmailAddress> =
            Either.cond(
                value.matches(Regex("""[^\s]+@[^\s]+""")),
                { UserEmailAddress(value) },
                { UserError.InvalidEmailAddressError }
            )
    }
}
