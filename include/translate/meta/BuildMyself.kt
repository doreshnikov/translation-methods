package translate.meta

import translate.meta.helpers.MetaVisitorBuilder
import translate.meta.helpers.MetaWalkerBuilder
import java.io.File

fun main() {

    val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"

    File("$loc\\include\\translate\\meta\\MetaVisitorBase.kt").bufferedWriter().use { out ->
        out.write(MetaWalkerBuilder.getAll())
    }
    File("$loc\\include\\translate\\meta\\MetaVisitor2Base.kt").bufferedWriter().use { out ->
        out.write(MetaVisitorBuilder.getAll())
    }

}