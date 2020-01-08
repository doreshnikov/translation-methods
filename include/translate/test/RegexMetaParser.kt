package translate.test

import parse.Parser
import translate.codegen.DescriptionBuilder
import translate.meta.MetaDescription
import java.io.File

fun main() {

    val str = File("src/translate/examples/regex.my").bufferedReader().use {
        return@use it.readText()
    }
    DescriptionBuilder().walk(Parser(MetaDescription).parse(str))

}