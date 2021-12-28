package edu.tpu.ruban.lib.basic

import java.util.*
import kotlin.collections.HashMap

class LinkedMap<K, V> : MutableMap<K, V> {
    private val map = HashMap<K, V>()
    private val keysList = LinkedList<K>()
    private val valuesList = LinkedList<V>()

    override val size: Int
        get() = map.size

    fun get(index: Int): K = keysList[index]
    fun getValue(index: Int): V = valuesList[index]

    fun indexOf(element: K): Int = keysList.indexOf(element)
    fun indexOfValue(element: V): Int = valuesList.indexOf(element)

    override fun isEmpty(): Boolean = keysList.isEmpty()

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>> = map.entries

    override val keys: MutableSet<K> = map.keys

    override val values: MutableCollection<V> = map.values

    override fun containsKey(key: K): Boolean = map.containsKey(key)

    override fun containsValue(value: V): Boolean = map.containsValue(value)

    override fun get(key: K): V? = map.get(key)

    override fun clear() {
        keysList.clear()
        valuesList.clear()
        map.clear()
    }

    override fun put(key: K, value: V): V? {
        val obj = map.put(key, value)
        if (obj != null) {
            keysList.remove(key)
            valuesList.remove(obj)
        }
        keysList.push(key)
        valuesList.push(obj)
        return obj
    }

    override fun putAll(from: Map<out K, V>) {
        for ((key, value) in from) {
            put(key, value)
        }
    }

    override fun remove(key: K): V? {
        val obj = map.remove(key)
        if (obj != null) {
            keysList.remove(key)
            valuesList.remove(obj)
        }
        keysList.push(key)
        valuesList.push(obj)
        return obj
    }

    fun removeAt(index: Int): V? {
        val key = keysList.removeAt(index)
        map.remove(key)
        return valuesList.removeAt(index)
    }
}