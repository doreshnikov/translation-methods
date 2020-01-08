package translate.test

import parse.Parser
import translate.codegen.DescriptionBuilder
import translate.codegen.DescriptionBuilderOld
import translate.meta.MetaDescription
import java.io.File

fun main() {

    val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"
    val str = File("$loc\\include\\translate\\examples\\regex.my").bufferedReader().use {
        return@use it.readText()
    }
    File("$loc\\src\\regex\\RegexOldDescription.kt").bufferedWriter().use { out ->
        out.write(
            DescriptionBuilderOld("regex", "RegexOld").walk(Parser(MetaDescription).parse(str))
        )
    }
    File("$loc\\src\\regex\\RegexNewDescription.kt").bufferedWriter().use { out ->
        out.write(
            DescriptionBuilder("regex", "RegexNew").collect(Parser(MetaDescription).parse(str))
        )
    }

}