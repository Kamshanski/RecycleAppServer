package com.example

import org.apache.commons.collections4.map.LinkedMap
import org.junit.runner.OrderWith
import java.util.*
import kotlin.collections.HashMap
import kotlin.random.Random
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ApplicationTest {
    @Test
    fun testPipa() {
//        PostgresDSL.select("*")
    }


    val map = HashMap<String, Pair<String, Int>>()
    val list = LinkedList<String>()
    val listValue = LinkedList<Pair<String, Int>>()

    val linkedMap = LinkedMap<String, Pair<String, Int>>()
    val linkedMapReverse = LinkedMap<Pair<String, Int>, String>()

    @BeforeTest
    fun prepare() {
        val loginSize = Random.nextInt(0, 30)
        val keySize = 512

        for (i in 0..200000) {
            val key = (0 until keySize).joinToString { Random.nextInt(10).toString() }
            val login = (0 until loginSize).joinToString { Random.nextInt(10).toString() }

            val pair = Pair(login, Random.nextInt())

            map[key] = pair
            list += key
            listValue += pair

            linkedMap[key] = pair
            linkedMapReverse[pair] = key

            assertEquals(map.size, list.size)
            assertEquals(map.size, listValue.size)
            assertEquals(map.size, linkedMap.size)
            assertEquals(map.size, linkedMapReverse.size)
        }
    }


    @Test
    fun testLinkedMap() {
        val startTime = System.currentTimeMillis()
        while (linkedMap.size > 0) {
            val key = linkedMap.get(0)
            val obj = linkedMap[key]
            linkedMap.remove(key)
            linkedMapReverse.remove(obj)
        }
        println("LinkedMap ${System.currentTimeMillis() -startTime}")
    }

    @Test
    fun testMapAndList() {
        val startTime = System.currentTimeMillis()
        while (list.size > 0) {
            val key = list.peek()
            val obj = map[key]
            list.pop()
            listValue.pop()
            map.remove(key)
        }
        println("Map + List ${System.currentTimeMillis() -startTime}")
    }
}