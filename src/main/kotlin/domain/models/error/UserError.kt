package domain.models.error

enum class UserError {
    InvalidEmailAddressError,
    IllegalEncryptedPasswordError,
    InvalidCharacterPasswordError,
    NotEnoughLengthPasswordError
}
