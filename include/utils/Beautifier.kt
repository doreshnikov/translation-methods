package utils

import kotlin.reflect.KClass

object Beautifier {

    fun println(any: Any) {
        kotlin.io.println(any).also { println() }
    }

    fun escape(value: String): String {
        var s = value
        mapOf(
            "\\" to "\\\\",
            "\"" to "\\\"",
            "\n" to "\\n",
            "\t" to "\\t",
            "\r" to "\\r"
        ).forEach { s = s.replace(it.key, it.value) }
        return s
    }

    fun detabify(value: String): String {
        return value.replace("\t", " ".repeat(4))
    }

    fun packageName(clazz: KClass<*>): String {
        return clazz.qualifiedName!!.split(".")
            .filter { it[0].isLowerCase() }.joinToString(".")
    }

    fun className(clazz: KClass<*>): String {
        return clazz.qualifiedName!!.split(".")
            .filter { it[0].isUpperCase() }.joinToString(".")
    }

}