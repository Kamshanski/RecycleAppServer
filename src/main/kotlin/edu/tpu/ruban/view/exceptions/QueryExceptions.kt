package edu.tpu.ruban.customLibs.serverexceptions

// base exception for queries
open class QueryException(message: String? = null, cause: Throwable? = null) : ServerException(message, cause)

open class IllegalQueryParameterException(name: String, value: Any?, cause: Throwable? = null) : QueryException("Query parameter $name cannot be '$value'", cause)

open class IllegalRouteParameterException(name: String, value: Any?, cause: Throwable? = null) : QueryException("Route parameter $name cannot be '$value'", cause)