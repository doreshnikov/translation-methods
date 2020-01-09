package translate.test

import parse.Parser
import translate.codegen.GrammarInfoBuilder
import translate.meta.MetaGrammarInfo
import java.io.File
import java.text.ParseException

fun singleUpperCase(string: String): String {
    return string[0].toUpperCase() + string.substring(1)
}

fun main() {

    val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"
    listOf("regex", "calculator", "prefix").forEach { name ->
        val str = File("$loc\\include\\translate\\examples\\$name.my").bufferedReader().use {
            return@use it.readText()
        }
        val uname = "${singleUpperCase(name)}GrammarInfo"
        try {
            val s = GrammarInfoBuilder("gen", uname)
                .collect(Parser(MetaGrammarInfo).parse(str))
            File("$loc\\src\\gen\\${uname}.kt").bufferedWriter().use { out ->
                out.write(s)
            }
            println("[SUCCESS] $name")
        } catch (e: ParseException) {
            println("[FAIL] $name: $e at ${e.errorOffset}")
        } catch (e: IllegalStateException) {
            println("[FAIL] $name: $e")
        }
    }

}