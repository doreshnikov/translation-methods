package translate.meta

import translate.codegen.AbstractVisitorBuilder
import java.io.File

fun main() {

    val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"
    AbstractVisitorBuilder(
        MetaDescription,
        "MetaDescription",
        "translate.meta",
        "MetaBaseVisitor2"
    ).buildVisitor(
        File("$loc\\include\\translate\\meta\\MetaBaseVisitor2.kt")
    )

}