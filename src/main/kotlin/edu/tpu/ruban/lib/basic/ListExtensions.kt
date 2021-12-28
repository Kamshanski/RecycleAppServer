package edu.tpu.ruban.lib.basic

inline fun <T> createList(block: MutableList<T>.() -> Unit) : List<T> = mutableListOf<T>().apply { block(this) }

inline fun <T> T.toList() : List<T> = listOf(this)
inline fun <T> T.toMutableList() : MutableList<T> = mutableListOf(this)