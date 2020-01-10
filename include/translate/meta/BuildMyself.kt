package translate.meta

import translate.codegen.WalkerBuilder
import translate.meta.helpers.MetaGrammarInfo
import translate.meta.helpers.MetaVisitorBuilder
import java.io.File

const val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"

fun main() {

    File("$loc\\include\\translate\\meta\\MetaWalkerBase.kt").bufferedWriter().use { out ->
        out.write(WalkerBuilder("translate.meta", MetaGrammarInfo).getAll())
    }
    File("$loc\\include\\translate\\meta\\MetaVisitorBase.kt").bufferedWriter().use { out ->
        out.write(MetaVisitorBuilder.getAll())
    }

}