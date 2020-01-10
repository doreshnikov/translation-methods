package translate.test

import parse.Parser
import translate.codegen.GrammarInfoBuilder
import translate.codegen.WalkerBuilder
import translate.codegen.helpers.GrammarInfo
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
            File("$loc\\src\\gen\\$name\\${uname}GrammarInfo.kt").bufferedWriter().use { out ->
                out.write(GrammarInfoBuilder("gen.$name", uname).collect(root))
            }
            File("$loc\\src\\gen\\$name\\${uname}WalkerBase.kt").bufferedWriter().use { out ->
                out.write(
                    WalkerBuilder(
                        "gen.$name",
                        Class.forName("gen.$name.${uname}GrammarInfo").kotlin.objectInstance as GrammarInfo
                    ).getAll()
                )
            }
//            VisitorBuilder(
//                "gen.$name",
//                "${uname}AttributedVisitorInfo",
//                "${uname}GrammarInfo"
//            ).doAll(root)
            println("[SUCCESS] $name")
        } catch (e: ParseException) {
            println("[FAIL] $name: $e at ${e.errorOffset}")
        } catch (e: IllegalStateException) {
            println("[FAIL] $name: $e")
        }
    }

}