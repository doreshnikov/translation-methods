package translate.codegen

import parse.Parser
import translate.codegen.meta.MetaDescription
import java.io.File

fun main() {

    val str = File("src/translate/examples/regex.my").bufferedReader().use {
        return@use it.readText()
    }
    Walker().walk(Parser(MetaDescription).parse(str))

}