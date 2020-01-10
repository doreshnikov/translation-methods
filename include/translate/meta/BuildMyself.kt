package translate.meta

import translate.meta.visitors.MetaAttributedVisitorInfo
import translate.meta.visitors.MetaVisitorInfo
import java.io.File

fun main() {

    val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"

    File("$loc\\include\\translate\\meta\\visitors\\MetaVisitorBase.kt").bufferedWriter().use { out ->
        out.write(MetaVisitorInfo.getAll())
    }
    File("$loc\\include\\translate\\meta\\visitors\\MetaAttributedVisitorBase.kt").bufferedWriter().use { out ->
        out.write(MetaAttributedVisitorInfo.getAll())
    }

}