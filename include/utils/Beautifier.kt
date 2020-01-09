package utils

import grammar.token.Token

class Beautifier {

    companion object {
        fun println(any: Any) {
            kotlin.io.println(any).also { println() }
        }

        fun escape(value: String): String {
            var s = value
            s = s.replace("\\", "\\\\")
            s = s.replace("\"", "\\\"")
            s = s.replace("\n", "\\n")
            s = s.replace("\t", "\\t")
            s = s.replace("\r", "\\r")
            return s
        }

        fun detabify(value: String): String {
            return value.replace("\t", " ".repeat(4))
        }
    }

}