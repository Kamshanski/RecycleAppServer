package edu.tpu.ruban.data.converter.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.time.LocalDateTime

object CommonGson {
    val commonGson: Gson = GsonBuilder().run {
        registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        serializeNulls()
        create()
    }
}