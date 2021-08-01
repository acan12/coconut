package app.beelabs.com.codebase.support.util

import com.fasterxml.jackson.databind.ObjectMapper

object DataUtil {
    fun convertJsonToString(json: Any): String {
        return ObjectMapper().writeValueAsString(json)
    }

    fun convertStringToJson(jsonstr: String, clazz: Class<out Any>): Any {
        return ObjectMapper().readValue(jsonstr, clazz)
    }
}