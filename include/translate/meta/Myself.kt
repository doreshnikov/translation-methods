package translate.meta

import translate.codegen.VisitorBuilder
import java.io.File

fun main() {

    val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"
    VisitorBuilder(
        MetaDescription,
        "MetaDescription",
        "translate.meta",
        "MetaBaseVisitor"
    ).buildVisitor(
        File("$loc\\include\\translate\\meta\\MetaBaseVisitor.kt")
    )

}