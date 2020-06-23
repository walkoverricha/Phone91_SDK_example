package com.phone91.sdk.utils

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

class JsonUtil {

    companion object {
        private val mapper = ObjectMapper()

        @Throws(Exception::class)
        fun <T> fromJson(value: ByteArray?, clazz: Class<T>?): T {
            return mapper.readValue(value, clazz)
        }

        @Throws(Exception::class)
        fun <T> fromJson(value: String?, clazz: Class<T>?): T {
            return mapper.readValue(value, clazz)
        }

        @Throws(Exception::class)
        fun asJson(value: Any?): String? {
            return mapper.writeValueAsString(value)
        }

        @Throws(Exception::class)
        fun <T> convert(value: JsonNode?, clazz: Class<T>?): T {
            return mapper.treeToValue(value, clazz)
        }

        @Throws(Exception::class)
        fun <T> convert(
            value: Map<String?, Any?>?,
            clazz: Class<T>?
        ): T {
            return mapper.convertValue(value, clazz)
        }
    }
}