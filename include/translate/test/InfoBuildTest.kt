package translate.test

import parse.Parser
import translate.codegen.AttributedVisitorInfoBuilder
import translate.codegen.GrammarInfoBuilder
import translate.codegen.VisitorInfoBuilder
import translate.meta.grammar.MetaGrammarInfo
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
            val s = GrammarInfoBuilder(
                "gen.$name",
                "${uname}GrammarInfo"
            ).collect(root)
            File("$loc\\src\\gen\\$name\\${uname}GrammarInfo.kt").bufferedWriter().use { out -> out.write(s) }
            VisitorInfoBuilder(
                "gen.$name",
                "${uname}VisitorInfo",
                "${uname}GrammarInfo"
            ).doAll(root)
            AttributedVisitorInfoBuilder(
                "gen.$name",
                "${uname}AttributedVisitorInfo",
                "${uname}GrammarInfo"
            ).doAll(root)
            println("[SUCCESS] $name")
        } catch (e: ParseException) {
            println("[FAIL] $name: $e at ${e.errorOffset}")
        } catch (e: IllegalStateException) {
            println("[FAIL] $name: $e")
        }
    }

}