package translate.test

import parse.Parser
import translate.codegen.GrammarInfoBuilder
import translate.meta.helpers.MetaGrammarInfo
import java.io.File
import java.text.ParseException

fun main() {

    listOf("regex", "calculator", "prefix").forEach { name ->
        val str = File("$loc\\include\\translate\\examples\\$name.my").bufferedReader().use {
            return@use it.readText()
        }
        val uname = singleUpperCase(name)
        try {
            val root = Parser(MetaGrammarInfo).parse(str)
            File("$loc\\gen\\$name\\${uname}GrammarInfo.kt").bufferedWriter().use { out ->
                out.write(GrammarInfoBuilder(name, uname).collect(root))
            }
            println("[SUCCESS] $name")
        } catch (e: ParseException) {
            println("[FAIL] $name: $e at ${e.errorOffset}")
        } catch (e: IllegalStateException) {
            println("[FAIL] $name: $e")
        }
    }

}