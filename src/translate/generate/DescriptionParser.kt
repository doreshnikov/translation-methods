package translate.generate

import parse.Parser
import structure.Tree
import java.io.File
import java.text.ParseException

fun main() {

    val str = File("src/translate/examples/regex.my").bufferedReader().use {
        return@use it.readText()
    }
    Walker().walk(Parser(MetaDescription).parse(str))

}