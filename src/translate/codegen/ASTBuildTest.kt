package translate.codegen

import parse.Parser
import regex.GraphvizVisitor
import translate.codegen.meta.MetaDescription
import java.io.File
import java.text.ParseException

fun main() {

    val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"
    listOf("regex", "calculator", "").forEach { name ->
        val str = File("src/translate/examples/$name.my").bufferedReader().use {
            return@use it.readText()
        }
        File("$loc\\data\\$name.gv").bufferedWriter().use { out ->
            try {
                out.write(GraphvizVisitor().collect(Parser(MetaDescription).parse(str)))
                println("[SUCCESS]")
                Runtime.getRuntime().exec(
                    "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe" +
                            " -Tpdf $loc\\data\\$name.gv" +
                            " -o $loc\\data\\$name.pdf"
                )
            } catch (e: ParseException) {
                println("[FAIL]: $e at ${e.errorOffset}")
                e.printStackTrace()
            }
        }
    }

}