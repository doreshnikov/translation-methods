package translate.meta

import translate.codegen.AbstractVisitorBuilder
import java.io.File

fun main() {

    val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"
    AbstractVisitorBuilder(
        MetaGrammarInfo,
        MetaVisitorInfo,
        "translate.meta",
        "MetaBaseVisitor"
    ).buildVisitor(
        File("$loc\\include\\translate\\meta\\MetaBaseVisitor.kt")
    )

}