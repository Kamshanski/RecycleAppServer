package edu.tpu.ruban.customLibs.serverexceptions

// base exception for auth
open class AuthException(message: String? = null, cause: Throwable? = null) : ServerException(message, cause)

open class UserAlreadyExistsException(login: String, cause: Throwable? = null) : ServerException("User $login already exists", cause)

open class IncorrectCredentialsException(cause: Throwable? = null) : ServerException("User login or password are incorrect", cause)




@Deprecated(
    message = "Error msg must give no info about login or password. Only for test usage",
    replaceWith = ReplaceWith("IncorrectCredentialsException"),
    level = DeprecationLevel.WARNING)
open class UserNotFoundException(login: String, cause: Throwable? = null) : ServerException("User $login was not found", cause)