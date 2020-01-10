package translate.test

import parse.Parser
import utils.GraphvizWalker
import translate.meta.helpers.MetaGrammarInfo
import java.io.File
import java.text.ParseException

fun singleUpperCase(string: String): String {
    return string[0].toUpperCase() + string.substring(1)
}

const val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"

fun main() {

    listOf("regex", "calculator", "prefix").forEach { name ->
        val str = File("include/translate/examples/$name.my").bufferedReader().use {
            return@use it.readText()
        }
        File("$loc\\data\\$name.gv").bufferedWriter().use { out ->
            try {
                out.write(GraphvizWalker().collect(Parser(MetaGrammarInfo).parse(str)))
                println("[SUCCESS] $name")
                Runtime.getRuntime().exec(
                    "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe" +
                            " -Tpdf $loc\\data\\$name.gv" +
                            " -o $loc\\data\\$name.pdf"
                )
            } catch (e: ParseException) {
                println("[FAIL] $name: $e at ${e.errorOffset}")
            }
        }
    }

}