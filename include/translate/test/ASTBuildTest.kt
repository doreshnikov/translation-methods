package translate.test

import parse.Parser
import utils.GraphvizVisitor
import translate.meta.MetaGrammarInfo
import java.io.File
import java.text.ParseException

fun main() {

    val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"
    listOf("regex", "calculator", "prefix").forEach { name ->
        val str = File("include/translate/examples/$name.my").bufferedReader().use {
            return@use it.readText()
        }
        File("$loc\\data\\$name.gv").bufferedWriter().use { out ->
            try {
                out.write(GraphvizVisitor().collect(Parser(MetaGrammarInfo).parse(str)))
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