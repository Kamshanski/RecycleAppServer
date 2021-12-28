package edu.tpu.ruban.lib.timerpool

import edu.tpu.ruban.lib.basic.LinkedMap
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.NoSuchElementException

class TimerMapPoolImpl<K : Any, V : Any> {

    private val container = LinkedMap<K, ScheduledObject<V>>()

    fun put(key: K, value: V, duration: Duration) = put(key, value, LocalDateTime.now() + duration)

    fun put(key: K, value: V, endTime: LocalDateTime) {
        synchronized(this) {
            container[key] = ScheduledObject(value, endTime)
        }
    }

    fun remove(key: K) {
        synchronized(this) {
            container.remove(key)
        }
    }

    fun removeValues (value: V) = synchronized(this) {
        container.values.removeIf { it.value == value }
    }


    fun getOrNull(key: K) : V? = synchronized(this) {
        container[key]?.let { obj ->
            val now = LocalDateTime.now()
            if (now < obj.endTime) {
                obj.value
            } else {
                container.remove(key)
                null
            }
        }
    }

    fun get(key: K) : V = getOrNull(key) ?: throw NoSuchElementException("No element with key $key")

    fun evictExpired() : Int = synchronized(this) {
        val counter = AtomicInteger(0)
        val now = LocalDateTime.now()
        while (container.size > 0) {
            val obj = container.getValue(0)
            if (obj.endTime < now) {
                container.removeAt(0)
                counter.incrementAndGet()
            } else {
                break
            }
        }
        return@synchronized counter.get()
    }

    val size: Int
        get() = container.size

    private class ScheduledObject<V: Any>(
        val value: V,
        val endTime: LocalDateTime
    )

}