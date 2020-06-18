package domain.models.user

import arrow.core.Either
import domain.models.error.UserError
import org.springframework.security.crypto.bcrypt.BCrypt

data class UserEncryptedPassword private constructor(private val value: String) {
    fun authenticate(password: UserPlainPassword): Boolean =
        BCrypt.checkpw(password.asString(), value)

    fun asString(): String = value

    companion object {
        operator fun invoke(value: String): Either<UserError, UserEncryptedPassword> =
            Either.cond(
                value.startsWith("""${'$'}2a${'$'}10"""),
                { UserEncryptedPassword(value) },
                { UserError.IllegalEncryptedPasswordError }
            )
    }
}
