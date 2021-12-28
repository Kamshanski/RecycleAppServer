package edu.tpu.ruban.data.converter.gson

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

class LocalDateTimeAdapter: JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDateTime? {
        return try {
            LocalDateTime.parse(json?.asString)
        } catch (dtpe: DateTimeParseException) {
            println("ERROR parsing LocalDateTime:")
            println(json.toString())
            LocalDateTime.MIN
        }
    }

    override fun serialize(src: LocalDateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        if (src == null) {
            return JsonNull.INSTANCE
        }
        return JsonPrimitive(src.toString())
    }
}