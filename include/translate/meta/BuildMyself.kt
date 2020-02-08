package translate.meta

import translate.codegen.VisitorBaseBuilder
import translate.codegen.WalkerBaseBuilder
import translate.meta.helpers.MetaGrammarInfo
import java.io.File

const val loc = "C:\\Users\\jetbrains\\IdeaProjects\\translation-methods"

fun main() {

    File("$loc\\include\\translate\\meta\\MetaWalkerBase.kt").bufferedWriter().use { out ->
        out.write(WalkerBaseBuilder("translate.meta", MetaGrammarInfo).getAll())
    }
    File("$loc\\include\\translate\\meta\\MetaVisitorBase.kt").bufferedWriter().use { out ->
        out.write(VisitorBaseBuilder("translate.meta", MetaGrammarInfo).getAll())
    }

}