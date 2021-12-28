package edu.tpu.ruban.customLibs.serverexceptions

// base exception for auth
open class InternalException(message: String? = null, cause: Throwable? = null) : ServerException(message, cause)

open class DatabaseRawQueryException(msg: String, query: String, cause: Throwable? = null) : ServerException("$msg. Query:\n$query", cause)

open class RawQueryReturnedNullResultException(query: String, cause: Throwable? = null)
    : DatabaseRawQueryException("Raw query returned null", query, cause)


