package domain.models.user

import domain.models.ActiveStatus
import java.time.Instant

data class User(
    val id: UserId,
    val emailAddress: UserEmailAddress,
    val encryptedPassword: UserEncryptedPassword,
    val passwordSalt: UserPasswordSalt,
    val activeStatus: ActiveStatus,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    fun authenticate(password: UserPlainPassword): Boolean =
        encryptedPassword.authenticate(password)
}
