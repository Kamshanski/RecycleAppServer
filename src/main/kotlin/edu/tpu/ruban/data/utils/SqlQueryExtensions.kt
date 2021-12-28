package edu.tpu.ruban.data.utils

fun StringBuilder.appendWhere(conditions: List<String>) : StringBuilder {
    if (conditions.isNotEmpty()) {
        append(" WHERE ")
        conditions.joinTo(this, separator = " AND ", prefix = " ", postfix = " ")
    }
    return this
}

fun StringBuilder.appendLimitOrNone(limit: Int) : StringBuilder {
    if (limit > 0) {
        append(" LIMIT ")
        append(limit)
    }
    return this
}