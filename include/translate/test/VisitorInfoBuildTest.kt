package translate.test

import parse.Parser
import translate.codegen.VisitorInfoBuilder
import translate.meta.MetaGrammarInfo
import java.io.File
import java.text.ParseException

fun main() {

    listOf("regex", "calculator", "prefix").forEach { name ->
        val str = File("$loc\\include\\translate\\examples\\$name.my").bufferedReader().use {
            return@use it.readText()
        }
        val uname = singleUpperCase(name)
        try {
            VisitorInfoBuilder(
                "gen.$name",
                "${uname}VisitorInfo",
                "${uname}GrammarInfo"
            ).doAll(Parser(MetaGrammarInfo).parse(str))
//            File("$loc\\src\\gen\\$name\\${uname}VisitorInfo.kt").bufferedWriter().use { out -> out.write(s) }
            println("[SUCCESS] $name")
        } catch (e: ParseException) {
            println("[FAIL] $name: $e at ${e.errorOffset}")
        } catch (e: IllegalStateException) {
            println("[FAIL] $name: $e")
        }
    }

}