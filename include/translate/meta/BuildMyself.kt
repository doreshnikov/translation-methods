package translate.meta

import java.io.File

fun main() {

    val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"

    File("$loc\\include\\translate\\meta\\MetaBaseVisitor.kt").bufferedWriter().use { out ->
        out.write(MetaVisitorInfo.getAll())
    }

}