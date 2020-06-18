package domain.models.user

import arrow.core.Either
import arrow.core.extensions.either.foldable.get
import arrow.core.extensions.fx
import arrow.core.getOrElse
import arrow.core.right
import domain.models.error.UserError
import org.springframework.security.crypto.bcrypt.BCrypt

data class UserPlainPassword private constructor(private val value: String) {
    fun encrypt(salt: UserPasswordSalt): UserEncryptedPassword =
        UserEncryptedPassword(BCrypt.hashpw(value, salt.asString()))
            .getOrElse { throw RuntimeException() }

    fun asString(): String = value

    companion object {
        operator fun invoke(value: String): Either<UserError, UserPlainPassword> =
            Either.fx {
                val e1 = Either.cond(
                    value.matches(Regex("""\p{ASCII}*""")),
                    { UserPlainPassword(value) },
                    { UserError.InvalidCharacterPasswordError }
                ).bind()
                val e2 = Either.cond(
                    value.length > 4,
                    { e1 },
                    { UserError.NotEnoughLengthPasswordError }
                ).bind()
                e2
            }
    }
}
