package domain.models.user

import org.springframework.security.crypto.bcrypt.BCrypt

data class UserPasswordSalt private constructor(private val value: String) {
    fun asString(): String = value

    companion object {
        operator fun invoke() = UserPasswordSalt(BCrypt.gensalt())
    }
}
