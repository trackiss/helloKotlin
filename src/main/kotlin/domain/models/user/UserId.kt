package domain.models.user

import java.util.*

data class UserId constructor(private val value: UUID = UUID.randomUUID()) {
    fun asString(): String = value.toString()
    fun asUuid(): UUID = value
}
